/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shopapplication.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.google.common.cache.Cache;

/**
 *
 * @author hidri_000
 */



public class ValidateCSRFTokenFilter extends HttpServlet implements Filter {


	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		// Assume its HTTP
		HttpServletRequest httpReq = (HttpServletRequest) request;

		// Get the salt sent with the request
		String salt = (String) httpReq.getParameter("csrfPreventionSalt");

		// Validate that the salt is in the cache
		Cache<String, Boolean> csrfPreventionSaltCache = (Cache<String, Boolean>) httpReq
				.getSession().getAttribute("csrfPreventionSaltCache");

		if (csrfPreventionSaltCache != null && salt != null
				&& csrfPreventionSaltCache.getIfPresent(salt) != null) {

		// If the salt is in the cache, we move on
			chain.doFilter(request, response);
		} else {
			// Otherwise we throw an exception aborting the request flow
			httpReq.getSession().invalidate();
			throw new ServletException(
					"Potential CSRF detected!");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
