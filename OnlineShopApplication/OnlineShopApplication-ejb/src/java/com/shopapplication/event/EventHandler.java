/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopapplication.event;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author hidri_000
 */
public interface EventHandler {

    abstract void execute(ServletRequest request, ServletResponse response) throws Exception;

    abstract String traceLog();

}
