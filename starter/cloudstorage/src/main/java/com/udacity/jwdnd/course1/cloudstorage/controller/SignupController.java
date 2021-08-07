package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("signup")
public class SignupController {
    private UserService userService;

    SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getSignup() {
        return "signup";
    }

    @PostMapping
    public String createSignup(@ModelAttribute("User") User user, Model model) {
        if (userService.addUser(user)) {
            model.addAttribute("userCreationStatus", "ok");
            return "login";
        } else {
            model.addAttribute("userCreationStatus", "failure");
            return "signup";
        }
    }
}
