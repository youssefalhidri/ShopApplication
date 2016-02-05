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

import com.shopapplication.model.Product;

/**
 *
 * @author hidri_000
 */
public class ProductManager {

    public static Product findById(int id) {

        SessionFactory sessionFactory = ApplicationSessionFactory.getInstance();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Product product = (Product) session.get(Product.class, id);
            return product;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static List<Product> find(String name, String category,
            String creationDate) {

        /**
         * Use of Prepared Statements to defend against SQL injection attacks.
         */
        SessionFactory sessionFactory = ApplicationSessionFactory.getInstance();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String hql = "";
            if (name != null) {
                hql += " product_name = :name";
            }
            if (category != null) {
                if (hql.equals("")) {
                    hql += " category = :category";
                } else {
                    hql += " and category = :category";
                }
            }
            if (creationDate != null) {
                if (hql.equals("")) {
                    hql += " creation_date = :creation_date";
                } else {
                    hql += " and creation_date = :creation_date";
                }
            }
            if (!hql.equals("")) {
                hql = "WHERE" + hql;
            }
            ;
            Query query = session.createQuery("FROM Product " + hql);
            if (name != null) {
                query.setParameter("name", name);
            }
            if (category != null) {
                query.setParameter("category", category);
            }
            if (creationDate != null) {
                query.setParameter("creation_date", creationDate);
            }
            List products = query.list();
            return products;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static void addComment(int id, String userName, String comment) {

        /**
         * Use of Prepared Statements to defend against SQL injection attacks.
         */
        if (comment == null || comment.isEmpty()) {
            return;
        }

        Product product = findById(id);
        String oldComment = product.getComment();
        SessionFactory sessionFactory = ApplicationSessionFactory.getInstance();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            /**
             * Use of Prepared Statements to defend against SQL injection
             * attacks.
             */
            String hql = "UPDATE Product set comment = :comment "
                    + "WHERE product_id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            comment = "<i>" + userName + "</i> says" + " : " + comment;
            if (oldComment != null) {
                comment = oldComment + "<br><br>" + comment;
            }
            query.setParameter("comment", comment);
            query.executeUpdate();
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

    public static void updateQuantity(int id, int quantity) {

        SessionFactory sessionFactory = ApplicationSessionFactory.getInstance();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            /**
             * Use of Prepared Statements to defend against SQL injection
             * attacks.
             */
            String hql = "UPDATE Product set quantity = :quantity "
                    + "WHERE product_id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            query.setParameter("quantity", quantity);
            query.executeUpdate();
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

    public static void addProduct(Product product) {
        SessionFactory sessionFactory = ApplicationSessionFactory.getInstance();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(product);
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

    public static void removeProduct(int id) {

        SessionFactory sessionFactory = ApplicationSessionFactory.getInstance();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            /**
             * Use of Prepared Statements to defend against SQL injection
             * attacks.
             */
            String hql = "DELETE FROM Product " + "WHERE product_id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            query.executeUpdate();
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
