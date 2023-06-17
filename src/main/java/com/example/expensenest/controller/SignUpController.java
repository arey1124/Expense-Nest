package com.example.expensenest.controller;

import com.example.expensenest.entity.User;
import com.example.expensenest.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {

    private UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String getSignUpPage (Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    @PostMapping("/user/create")
    public String createUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        // TODO: Need to redirect to password setup page
        return "redirect:/signup";
    }

}
