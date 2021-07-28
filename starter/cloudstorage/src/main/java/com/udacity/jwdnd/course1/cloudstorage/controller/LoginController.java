package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginPage(
            @RequestParam(required = false, name = "logout") boolean logout,
            @RequestParam(required = false, name = "error") String error,
            Model model) {

        //login failed (non-existing user / wrong credentials)
        if (error == "") {
            model.addAttribute("loginSuccessStatus", "failure");
        }
        //logout successful
        if (logout) {
            model.addAttribute("logoutSuccessStatus", "success");
        }
        return "login";
    }
}
