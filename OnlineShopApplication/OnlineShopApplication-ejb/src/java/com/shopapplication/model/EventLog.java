/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopapplication.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author hidri_000
 */
@Entity
@Table(name = "event_log")
public class EventLog implements Serializable {

    @Column(name = "user_id")
    String userID;

    @Column(name = "event")
    String event;

    @Column(name = "trace", columnDefinition = "Text")
    String trace;

    @Column(name = "creation_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    Date creationDate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long rowID;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getRowID() {
        return rowID;
    }

    public void setRowID(Long rowID) {
        this.rowID = rowID;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }
}
