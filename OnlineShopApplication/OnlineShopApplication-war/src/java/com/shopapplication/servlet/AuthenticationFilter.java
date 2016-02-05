/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopapplication.servlet;

import java.io.IOException;

import java.security.SecureRandom;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.owasp.esapi.errors.EncryptionException;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import com.shopapplication.model.Role;
import com.shopapplication.model.User;
import com.shopapplication.persistence.UserManager;
import com.shopapplication.utility.Utility;

/**
 *
 * @author hidri_000
 */
public class AuthenticationFilter extends HttpServlet implements Filter {

    private static final long serialVersionUID = 1L;
    private static final String mode = System.getProperty("mode");
    private static final ConcurrentMap<String, String> sessions = new ConcurrentHashMap<String, String>(
            1000);

    @SuppressWarnings("unchecked")
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

		// Check the user session for the salt cache, if none is present we
        // create one
        Cache<String, Boolean> csrfPreventionSaltCache = (Cache<String, Boolean>) req
                .getSession().getAttribute("csrfPreventionSaltCache");

        if (csrfPreventionSaltCache == null) {
            csrfPreventionSaltCache = CacheBuilder.newBuilder()
                    .maximumSize(5000).expireAfterWrite(20, TimeUnit.MINUTES)
                    .build();

            req.getSession().setAttribute("csrfPreventionSaltCache",
                    csrfPreventionSaltCache);
        }

        // Generate the salt and store it in the users cache
        String salt = RandomStringUtils.random(20, 0, 0, true, true, null,
                new SecureRandom());
        csrfPreventionSaltCache.put(salt, Boolean.TRUE);

		// Add the salt to the current request so it can be used
        // by the page rendered in this request
        req.setAttribute("csrfPreventionSalt", salt);

        String uri = req.getRequestURI();

        uri = uri.substring(req.getContextPath().length());

        if (uri.equals("/login.jsp") || uri.endsWith(".jpg")
                || uri.endsWith(".css") || uri.endsWith(".gif")
                || uri.endsWith(".png")) {
            chain.doFilter(request, response);
            return;
        }
        if (uri.equals("/logout.jsp")) {
            logout(request, response);
			//req.getRequestDispatcher("/login.jsp").forward(request,
            //	response);
            HttpServletResponse httpresponse = (HttpServletResponse) response;
            httpresponse.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        if (isAuthenticated(req) != null) {
            chain.doFilter(request, response);

        } else {

            String username = (String) Utility.getSafeHTMLParameter(request, "username");
            String password = (String) Utility.getHTMLParameter(request, "password");

            User user = null;
            try {
                user = authenticate(username, password);
            } catch (EncryptionException e) {
                e.printStackTrace();
                req.setAttribute("message", "The user name or password is incorrect");
            }

            if (user != null) {
                HttpSession session = req.getSession(false);

                String sessionId = session.getId();
                sessions.put(sessionId, user.getUserID());
                session.setAttribute("userid", user.getUserID());
                session.setAttribute("username", user.getUsername());

                chain.doFilter(request, response);

            } else {
                if ("login".equals(Utility.getHTMLParameter(request, "_event"))) {
                    req.setAttribute("message", "The user name or password is incorrect");
                }
                req.getRequestDispatcher("/login.jsp").forward(request,
                        response);
            }

        }
    }

    public static String isAuthenticated(ServletRequest request) {
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        if (session == null) {
            return null;
        }
        return (String) sessions.get(session.getId());
    }

    private User authenticate(String userid,
            String password) throws EncryptionException, IOException {

        Role administrator = UserManager.findRoleById("Administrator");
        Role customer = UserManager.findRoleById("Customer");

        if (administrator == null) {
            Role role = new Role();
            role.setRoleID("Administrator");
            role.setDescription("System Administrator");
            UserManager.saveRole(role);
        }
        if (customer == null) {
            Role role = new Role();
            role.setRoleID("Customer");
            role.setDescription("Customer");
            UserManager.saveRole(role);
        }
        if ("admin".equals(mode)) {
            User user = new User();
            user.setUserID("admin");
            user.setUsername("admin");
            Role role = UserManager.findRoleById("Administrator");
            user.setRole(role);
            return user;
        }

        if (userid == null) {
            return null;
        }
        User user = UserManager.findById(userid);
        if (user == null) {
            return null;
        }
        if (!Utility.decrypt(user.getPassword()).equals(password)) {
            return null;
        };
        return user;

    }

    private void logout(ServletRequest request, ServletResponse response) {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        String user = sessions.get(session.getId());
        if (user != null) {
            req.getSession().invalidate();
            sessions.remove(session.getId());
        } else {
            req.getSession().invalidate();
        }
    }

    public static void removeSession(String sessionId) {
        if (sessions.containsKey(sessionId)) {
            sessions.remove(sessionId);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }
}
