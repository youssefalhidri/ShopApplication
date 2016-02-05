/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopapplication.action;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.shopapplication.model.ShoppingCart;
import com.shopapplication.model.ShoppingOrder;

/**
 *
 * @author hidri_000
 */
public class ViewShoppingCartActionHandler extends ActionHandler {

    @Override
    public void execute(ServletRequest request, ServletResponse response) throws Exception {
        ShoppingCart shoppingCart = (ShoppingCart) ((HttpServletRequest) request).getSession().getAttribute("shoppingCart");
        if (shoppingCart == null) {
            return;
        }
        Map<String, ShoppingOrder> orders = shoppingCart.getOrders();
        request.setAttribute("orders", orders.values());
    }

}
