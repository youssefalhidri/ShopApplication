/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shopapplication.security;

import java.util.Set;

import com.shopapplication.event.EventFactory;
import com.shopapplication.model.Role;
import com.shopapplication.model.User;
import com.shopapplication.persistence.UserManager;

/**
 *
 * @author hidri_000
 */

public class SecurityAuthorization {

	private static final String mode = System.getProperty("mode");
        
	private static String getUserRole(String userName) throws Exception {
		
		
		if ("admin".equals(mode)){
			return "Administrator";
		}
		
		User user = UserManager.findById(userName);
        
		Role rls = user.getRole();

		
		if(rls==null) {
			return null ;
		}
		return rls.getRoleID();

	}

	public static boolean isAuthorizedEvent(String userName, String event)
			throws Exception {

		if (userName == null || event == null) {
			return false;
		}
		Set<String> authorizedRoles = EventFactory.getAuthorizedRoles(event);

		if (authorizedRoles == null)
			return false;

		boolean authorized = false;

		String rls = getUserRole(userName);
		for (String role : authorizedRoles) {
			if (role.equals(rls)) {
				authorized = true;
				break;
			}
		}

		return authorized;
	}

}
