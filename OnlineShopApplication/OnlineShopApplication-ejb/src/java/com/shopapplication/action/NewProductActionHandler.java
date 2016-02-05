/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopapplication.action;

import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.shopapplication.model.Product;
import com.shopapplication.persistence.ProductManager;
import com.shopapplication.utility.Utility;

/**
 *
 * @author hidri_000
 */
public class NewProductActionHandler extends ActionHandler {

    @Override
    public void execute(ServletRequest request, ServletResponse response) throws Exception {

        String name = Utility.getHTMLParameter(request, "product_name");
        String description = Utility.getHTMLParameter(request, "description");
        String quantity = Utility.getHTMLParameter(request, "quantity");
        String price = Utility.getHTMLParameter(request, "price");
        String currency = Utility.getHTMLParameter(request, "currency");
        String category = Utility.getHTMLParameter(request, "category");

        Product product = new Product();
        product.setCategory(category);
        product.setCurrency(currency);
        product.setDescription(description);
        product.setProductName(name);
        product.setPrice(Float.parseFloat(price));
        product.setQuantity(Integer.parseInt(quantity));
        product.setCreationDate(new Date());
        ProductManager.addProduct(product);

        request.setAttribute("message", "Product saved successfully");

    }
}
