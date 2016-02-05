/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopapplication.action;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.shopapplication.event.EventFactory;

/**
 *
 * @author hidri_000
 */
@Startup
@Singleton
public class ShopApplicationInitializer {

    @PostConstruct
    public static void init() {

        ActionFactory.registerAction("search_product", "search", new SearchProductActionHandler());
        ActionFactory.registerAction("edit_product", "edit", new EditProductActionHandler());
        ActionFactory.registerAction("view_product", "view", new ViewProductActionHandler());
        ActionFactory.registerAction("remove_product", "remove", new RemoveProductActionHandler());
        ActionFactory.registerAction("add_comment", "add_comment", new AddCommentActionHandler());
        ActionFactory.registerAction("submit_new_product", "submit_new_product", new NewProductActionHandler());
        ActionFactory.registerAction("add_order", "add_order", new AddShoppingCartActionHandler());
        ActionFactory.registerAction("remove_order", "remove_order", new RemoveShoppingCartActionHandler());
        ActionFactory.registerAction("confirm_order", "confirm_order", new ConfirmShoppingCartOrderActionHandler());
        ActionFactory.registerAction("update_product", "update_product", new UpdateProductActionHandler());
        ActionFactory.registerAction("view_shopping_cart", "view_shopping_cartt", new ViewShoppingCartActionHandler());
        ActionFactory.registerAction("submit_new_user", "submit_new_user", new SaveUserActionHandler());

        Set<String> administrators = new HashSet<String>();
        administrators.add("Administrator");
        Set<String> customers = new HashSet<String>();
        customers.add("Customer");
        Set<String> customersAndAdministrators = new HashSet<String>();
        customersAndAdministrators.add("Customer");
        customersAndAdministrators.add("Administrator");
        try {
            EventFactory.registerEvent("search_product", customersAndAdministrators, "index.jsp", false);
            EventFactory.registerEvent("view_product", customersAndAdministrators, "view_product.jsp", false);
            EventFactory.registerEvent("edit_product", administrators, "edit_product.jsp", false);
            EventFactory.registerEvent("add_new_product", administrators, "new_product.jsp", false);
            EventFactory.registerEvent("submit_new_product", administrators, "success.jsp", true);
            EventFactory.registerEvent("remove_product", administrators, "index.jsp", true);
            EventFactory.registerEvent("add_order", customers, "success.jsp", false);
            EventFactory.registerEvent("remove_order", customers, "shopping_cart.jsp", true);
            EventFactory.registerEvent("confirm_order", customers, "success.jsp", true);
            EventFactory.registerEvent("view_shopping_cart", customers, "shopping_cart.jsp", false);
            EventFactory.registerEvent("add_comment", customers, "view_product.jsp", false);
            EventFactory.registerEvent("update_product", administrators, "success.jsp", false);
            EventFactory.registerEvent("login", customersAndAdministrators, "index.jsp", false);
            EventFactory.registerEvent("add_user", administrators, "add_user.jsp", false);
            EventFactory.registerEvent("submit_new_user", administrators, "add_user.jsp", false);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
