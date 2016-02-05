/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopapplication.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateful;

/**
 *
 * @author hidri_000
 */
@Stateful
public class ShoppingCart implements Serializable {

    private Map<String, ShoppingOrder> orders = new HashMap<String, ShoppingOrder>(0);

    public Map<String, ShoppingOrder> getOrders() {
        return orders;
    }

    public void setOrders(Map<String, ShoppingOrder> orders) {
        this.orders = orders;
    }

}
