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
import com.shopapplication.model.ShoppingCart;
import com.shopapplication.utility.Utility;

/**
 *
 * @author hidri_000
 */
public class RemoveShoppingCartActionHandler extends ActionHandler {

    static Properties props;
    static InitialContext ctx;

    static {
        props = new Properties();
        try {
            props.load(EventLogger.class.getClassLoader().getResourceAsStream("com/shopapplication/resources/jndi.properties"));
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
        String product_id = Utility.getHTMLParameter(request,"product_id");
        if (shoppingCart == null) {
            return;
        } else {
            shoppingCart.getOrders().remove((product_id));
        }

    }

}
