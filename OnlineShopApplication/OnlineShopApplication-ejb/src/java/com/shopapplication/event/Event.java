/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopapplication.event;

/**
 *
 * @author hidri_000
 */
public class Event {

    String eventCode;
    boolean auditable;
    String target;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setAuditable(boolean auditable) {
        this.auditable = auditable;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public boolean isAuditable() {
        return auditable;
    }

    public void setAuditble(boolean auditable) {
        this.auditable = auditable;
    }
}
