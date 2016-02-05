/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopapplication.action;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.shopapplication.model.Product;
import com.shopapplication.model.ShoppingCart;
import com.shopapplication.model.ShoppingOrder;
import com.shopapplication.persistence.ProductManager;
import com.shopapplication.persistence.ShoppingCartManager;
import com.shopapplication.utility.Utility;

/**
 *
 * @author hidri_000
 */
public class ConfirmShoppingCartOrderActionHandler extends ActionHandler {

    @Override
    public void execute(ServletRequest request, ServletResponse response) throws Exception {

        ShoppingCart shoppingCart = (ShoppingCart) ((HttpServletRequest) request)
                .getSession().getAttribute("shoppingCart");
        String product_id = Utility.getHTMLParameter(request, "product_id");
        ShoppingOrder order = shoppingCart.getOrders().get(product_id);
        Product product = ProductManager.findById(Integer.parseInt(product_id));
        int order_id = order.getOrderID();
        if (product.getQuantity() < order.getQuantity()) {
            String message = "The quantity " + order.getQuantity() + " of the order " + order_id + "  is out of stock";
            request.setAttribute("message", message);
            throw new Exception(message);
        } else {
            ShoppingCartManager.confirmOrder(order);
            shoppingCart.getOrders().remove((product_id));
            request.setAttribute("message", "Order number " + order.getOrderID() + " has been confirmed successfuly");
        }
    }

}
