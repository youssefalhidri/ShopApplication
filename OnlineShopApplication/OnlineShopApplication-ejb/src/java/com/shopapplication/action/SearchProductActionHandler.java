/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopapplication.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;

import com.shopapplication.model.Product;
import com.shopapplication.persistence.ProductManager;
import com.shopapplication.utility.Utility;

/**
 *
 * @author hidri_000
 */
public class SearchProductActionHandler extends ActionHandler {

    @Override
    public void execute(ServletRequest request, ServletResponse response) throws Exception {

        String name = Utility.getSafeHTMLParameter(request, "product_name");
        String id = Utility.getSafeHTMLParameter(request, "product_id");
        String creation_date = Utility.getSafeHTMLParameter(request, "creation_date");
        String category = Utility.getSafeHTMLParameter(request, "category");

        List<Product> products = new ArrayList<Product>();

        if (id != null) {

            Product product = ProductManager
                    .findById(Integer.parseInt(id));
            if (product != null) {
                products.add(product);
            }

        } else {

            products = ProductManager.find(name, category, creation_date);

        }

        ((HttpServletRequest) request).getSession().setAttribute("products", products);

    }

}
