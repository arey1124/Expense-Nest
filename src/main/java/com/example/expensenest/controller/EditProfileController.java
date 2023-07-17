package com.example.expensenest.controller;

import com.example.expensenest.entity.User;
import com.example.expensenest.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EditProfileController {

    private UserService userService;
    @PostMapping("/user/edit")
    public String createUser(@ModelAttribute("user") User user) {
        userService.editUser(user);
        return "editProfile";
    }
}
