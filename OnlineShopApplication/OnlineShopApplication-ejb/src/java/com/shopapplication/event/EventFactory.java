/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopapplication.event;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 *
 * @author hidri_000
 */
public class EventFactory {

    private final static ConcurrentMap<String, Set<String>> eventsAuthorizedRoles = new ConcurrentHashMap<String, Set<String>>();
    private final static ConcurrentMap<String, Event> events = new ConcurrentHashMap<String, Event>();

    public static void registerEvent(String eventCode,
            Set<String> roles, String target, boolean auditable) throws Exception {
        if (eventCode != null) {
            if (!events.containsKey(eventCode)) {
                Event event = new Event();
                event.setEventCode(eventCode);
                event.setAuditble(auditable);
                event.setTarget(target);
                events.put(eventCode, event);
                eventsAuthorizedRoles.put(eventCode, roles);
            }
        }
    }

    public static Set<String> getAuthorizedRoles(String event) {
        return eventsAuthorizedRoles.get(event);
    }

    public static Event getEvent(String eventCode) {
        return events.get(eventCode);
    }
}
