package com.udacity.jwdnd.course1.cloudstorage.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Utilities {
    private UserService userService;
    private Integer currentUserId;

    public Utilities(UserService userService) {
        this.userService = userService;
    }


    public Integer getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            currentUserId = userService.getUser(auth.getPrincipal().toString()).getUserId();
        } else {
            currentUserId = null;
        }
        return currentUserId;
    }
}
