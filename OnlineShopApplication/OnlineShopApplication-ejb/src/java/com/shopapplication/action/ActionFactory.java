/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopapplication.action;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 *
 * @author hidri_000
 */
public class ActionFactory {

    private static ConcurrentMap<String, ActionHandler> actions = new ConcurrentHashMap<String, ActionHandler>();

    public static void registerAction(String eventCode, String actionName, ActionHandler action) {
        if (eventCode == null || actionName == null
                || actions.containsKey(eventCode)) {
            return;
        } else {
            action.setActionName(actionName);
            action.setEventCode(eventCode);
            actions.put(eventCode, action);
        }
    }

    public static ActionHandler getAction(String eventCode) {
        return actions.get(eventCode);
    }

}
