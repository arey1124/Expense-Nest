package com.example.expensenest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInController {
    @GetMapping("/signin")
    public String getSignInPage (Model model) {
        model.addAttribute("signin", null);
        return "signin";
    }

}
