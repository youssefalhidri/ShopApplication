/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopapplication.action;

import java.io.IOException;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.shopapplication.logging.EventLogger;
import com.shopapplication.model.Product;
import com.shopapplication.model.ShoppingCart;
import com.shopapplication.model.ShoppingOrder;
import com.shopapplication.persistence.ProductManager;

/**
 *
 * @author hidri_000
 */
public class AddShoppingCartActionHandler extends ActionHandler {

    static Properties props;
    static InitialContext ctx;

    static {
        props = new Properties();
        try {
            props.load(EventLogger.class.getClassLoader().getResourceAsStream("com/shopapplication/resources/ShopApplication.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            ctx = new InitialContext(props);
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void execute(ServletRequest request, ServletResponse response) throws Exception {

        ShoppingCart shoppingCart = (ShoppingCart) ((HttpServletRequest) request)
                .getSession().getAttribute("shoppingCart");
        if (shoppingCart == null) {
            try {
                String shoppingCartJndi = props.getProperty("ShoppingCartEJBJndi");
                shoppingCart = (ShoppingCart) ctx.lookup(shoppingCartJndi);
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        String product_id = request.getParameter("product_id");
        String amount = request.getParameter("amount");
        Product product = ProductManager.findById(Integer.parseInt(product_id));
        String userID = (String) ((HttpServletRequest) request).getSession().getAttribute("userid");
        ShoppingOrder order = new ShoppingOrder();
        order.setQuantity(Integer.parseInt(amount));
        order.setProduct(product);
        order.setCustomer(userID);

        ShoppingOrder olderOrder = shoppingCart.getOrders().get(product_id);
        if (olderOrder != null) {
            olderOrder.setQuantity(olderOrder.getQuantity() + order.getQuantity());
        } else {
            shoppingCart.getOrders().put(product_id, order);
        }
        ((HttpServletRequest) request).getSession().setAttribute("shoppingCart", shoppingCart);
        request.setAttribute("message", "Product added to shopping cart successfully");

    }

}
