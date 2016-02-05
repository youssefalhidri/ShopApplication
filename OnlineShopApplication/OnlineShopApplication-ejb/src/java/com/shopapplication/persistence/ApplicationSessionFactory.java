/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopapplication.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * @author hidri_000
 */
public class ApplicationSessionFactory {

    private static final SessionFactory sessionFactory;

    static {

        Configuration configuration = new Configuration()
                .configure(ApplicationSessionFactory.class.getClassLoader()
                        .getResource("com/shopapplication/resources/hibernate.cfg.xml"));
        ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder();
        serviceRegistryBuilder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = serviceRegistryBuilder
                .buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

    }

    public static SessionFactory getInstance() {
        return sessionFactory;
    }
}
