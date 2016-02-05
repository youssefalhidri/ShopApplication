/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopapplication.logging;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.shopapplication.model.EventLog;

/**
 *
 * @author hidri_000
 */
public class EventLogger {

    static Properties props;
    static Properties shopApplicationProps;
    static InitialContext ctx;

    static {
        props = new Properties();
        shopApplicationProps = new Properties();
        try {
            props.load(EventLogger.class.getClassLoader().getResourceAsStream("com/shopapplication/resources/jndi.properties"));
            shopApplicationProps.load(EventLogger.class.getClassLoader().getResourceAsStream("com/shopapplication/resources/ShopApplication.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            ctx = new InitialContext(props);
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
    }

    public static void logEvent(String userId, String eventCode, String trace) {

        Queue queue;
        QueueConnectionFactory factory;
        QueueConnection connection = null;
        QueueSession session = null;
        QueueSender sender = null;

        try {
            String loggingQueue = shopApplicationProps.getProperty("LoggingQueue");
            queue = (Queue) ctx.lookup(loggingQueue);
            String queueConnectionFactory = shopApplicationProps.getProperty("QueueConnectionFactory");
            factory = (QueueConnectionFactory) ctx
                    .lookup(queueConnectionFactory);

            connection = factory.createQueueConnection();

            session = connection.createQueueSession(false,
                    QueueSession.AUTO_ACKNOWLEDGE);
            sender = session.createSender(queue);

            EventLog eventTrace = new EventLog();
            eventTrace.setUserID(userId);
            eventTrace.setTrace(trace);
            eventTrace.setEvent(eventCode);
            eventTrace.setCreationDate(new Date());
            ObjectMessage objectMessage = session
                    .createObjectMessage(eventTrace);
            sender.send(objectMessage);
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            if (sender != null) {
                try {
                    sender.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
