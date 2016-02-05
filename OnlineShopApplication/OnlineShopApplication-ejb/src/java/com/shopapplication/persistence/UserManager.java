/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopapplication.persistence;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.shopapplication.model.Role;
import com.shopapplication.model.User;

/**
 *
 * @author hidri_000
 */
public class UserManager {

    public static User findById(String userName) {

        SessionFactory sessionFactory = ApplicationSessionFactory.getInstance();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            User user = (User) session.get(User.class, userName);
            return user;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @SuppressWarnings("rawtypes")
    public static User authenticate(String username, String password) {
        /**
         * Use of Prepared Statements to defend against SQL injection attacks.
         */

        SessionFactory sessionFactory = ApplicationSessionFactory.getInstance();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String hql = " FROM User WHERE user_id = :username and password= :password";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            query.setParameter("password", password);
            List users = query.list();
            if (users != null && users.size() == 1) {
                return (User) users.iterator().next();
            } else {
                return null;
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static Role findRoleById(String role_id) {
        SessionFactory sessionFactory = ApplicationSessionFactory.getInstance();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Role role = (Role) session.get(Role.class, role_id);
            return role;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static void saveUser(User user) {
        SessionFactory sessionFactory = ApplicationSessionFactory.getInstance();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public static void saveRole(Role role) {
        SessionFactory sessionFactory = ApplicationSessionFactory.getInstance();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(role);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void updateRole(Role role) {
        SessionFactory sessionFactory = ApplicationSessionFactory.getInstance();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(role);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

}
