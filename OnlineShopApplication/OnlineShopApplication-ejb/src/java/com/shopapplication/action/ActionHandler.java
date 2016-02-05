/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopapplication.action;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.shopapplication.event.EventHandler;

/**
 *
 * @author hidri_000
 */
public abstract class ActionHandler implements EventHandler {

    String actionName;
    String eventCode;
    String group;

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    abstract public void execute(ServletRequest request, ServletResponse response) throws Exception;

    @Override
    public String traceLog() {
        return "executing event :" + getEventCode();
    }

}
