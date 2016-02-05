/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopapplication.action;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.shopapplication.model.Role;
import com.shopapplication.model.User;
import com.shopapplication.persistence.UserManager;
import com.shopapplication.utility.Utility;

/**
 *
 * @author hidri_000
 */
public class SaveUserActionHandler extends ActionHandler {

    @Override
    public void execute(ServletRequest request, ServletResponse response) throws Exception {

        String userid = Utility.getHTMLParameter(request, "user_id");
        String username = Utility.getHTMLParameter(request, "username");
        String password = Utility.getHTMLParameter(request, "password");
        String email = Utility.getHTMLParameter(request, "email");
        String role_id = Utility.getHTMLParameter(request, "role");
        Role role = UserManager.findRoleById(role_id);
        User user = new User();
        user.setEmail(email);
        user.setUserID(userid);
        user.setUsername(username);
        user.setRole(role);
        user.setPassword(Utility.encrypt(password));
        UserManager.saveUser(user);

    }
}
