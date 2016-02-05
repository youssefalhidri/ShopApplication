/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shopapplication.servlet;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
 
/**
 *
 * @author hidri_000
 */

public class SessionListener implements HttpSessionListener {
    
 
    public void sessionCreated(HttpSessionEvent event) {
    }
 /**
  * This method is executed when the session is timed out.
  */
    public void sessionDestroyed(HttpSessionEvent event) {
    	AuthenticationFilter.removeSession(event.getSession().getId());
    }
}