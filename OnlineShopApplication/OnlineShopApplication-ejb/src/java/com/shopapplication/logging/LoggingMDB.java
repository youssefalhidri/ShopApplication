/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopapplication.logging;

import javax.annotation.Resource;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.ejb.MessageDriven;
import javax.ejb.ActivationConfigProperty;

import com.shopapplication.model.EventLog;
import com.shopapplication.persistence.EventLogManager;

/**
 *
 * @author hidri_000
 */
@MessageDriven(
        name = "LoggingMDB",
        activationConfig = {
            @ActivationConfigProperty(propertyName = "destinationType",
                    propertyValue = "javax.jms.Queue"),
            @ActivationConfigProperty(propertyName = "destination",
                    propertyValue = "queue/LoggingQueue")
        }
)
public class LoggingMDB implements MessageListener {

    @Resource
    private MessageDrivenContext mdctx;

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage ;
        try {
            objectMessage = (ObjectMessage) message;
            EventLog eventLog = (EventLog) objectMessage.getObject();
            EventLogManager.saveEventLog(eventLog);

        } catch (JMSException ex) {
            ex.printStackTrace();
            mdctx.setRollbackOnly();
        }
    }

}
