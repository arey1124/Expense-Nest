package com.example.expensenest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class SignUpController {

    @GetMapping("/signup")
    public String getSignUpPage (Model model) {
        model.addAttribute("signup", null);
        return "signup";
    }

}
