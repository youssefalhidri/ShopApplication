/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopapplication.action;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.shopapplication.persistence.ProductManager;
import com.shopapplication.utility.Utility;

/**
 *
 * @author hidri_000
 */
public class AddCommentActionHandler extends ActionHandler {

    @Override
    public void execute(ServletRequest request, ServletResponse response) throws Exception {

        String comment = Utility.getSafeHTMLParameter(request, "comment");
        String id = Utility.getHTMLParameter(request, "product_id");
        String userName = (String) ((HttpServletRequest) request).getSession().getAttribute("username");
        ProductManager.addComment(Integer.parseInt(id), userName, comment);
        request.setAttribute("product", ProductManager.findById(Integer.parseInt(id)));
    }

}
