/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopapplication.action;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.shopapplication.model.Product;
import com.shopapplication.persistence.ProductManager;
import com.shopapplication.utility.Utility;

/**
 *
 * @author hidri_000
 */
public class EditProductActionHandler extends ActionHandler {

    @Override
    public void execute(ServletRequest request, ServletResponse response) throws Exception {

        String product_id = Utility.getHTMLParameter(request, "product_id");
        Product product = ProductManager.findById(Integer.parseInt(product_id));
        request.setAttribute("product", product);

    }

}
