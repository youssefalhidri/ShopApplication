/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shopapplication.servlet;

/**
 *
 * @author hidri_000
 */
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.bytecode.buildtime.spi.ExecutionException;

import com.shopapplication.action.ActionFactory;
import com.shopapplication.action.ActionHandler;
import com.shopapplication.event.Event;
import com.shopapplication.event.EventFactory;
import com.shopapplication.logging.EventLogger;
import com.shopapplication.security.SecurityAuthorization;
import com.shopapplication.utility.Utility;

public class ShopApplicationServlet extends HttpServlet {

	 
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {

	};

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		handleRequest(request, response);

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		handleRequest(request, response);

	}

	private void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			String event = Utility.getHTMLParameter(request, "_event");
			String user = AuthenticationFilter.isAuthenticated(request);
			if (SecurityAuthorization.isAuthorizedEvent(user, event)) {
				handleEvent(request, response, event);
			}else {
				throw new ExecutionException("You are not authorized to perform this action");
			}

			RequestDispatcher dispacher = request
					.getRequestDispatcher(EventFactory.getEvent(event)
							.getTarget());
			dispacher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}
	}

	private void handleEvent(ServletRequest request, ServletResponse response,
			String event) throws Exception {
		Event newEvent = EventFactory.getEvent(event);
		ActionHandler action = ActionFactory.getAction(event);

		if (newEvent.isAuditable()) {
			EventLogger.logEvent(AuthenticationFilter.isAuthenticated(request),
					event, action == null ? "" : action.traceLog());
		}

		if (action != null)
			action.execute(request, response);

	}

}