package com.example.expensenest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignInController {
//    @GetMapping("/signin")
//    public String getSignInPage (Model model) {
//        model.addAttribute("signin", null);
//        return "signin";
//    }

    @GetMapping("/signin")
    public ModelAndView showLoginForm(String message, String messageType) {
        ModelAndView modelAndView = new ModelAndView("signin");
        modelAndView.addObject("message", message);
        modelAndView.addObject("messageType", messageType);
        return modelAndView;
    }

    @PostMapping("/signinpost")
    public ModelAndView login(String email, String password) {
        ModelAndView modelAndView = new ModelAndView();
        if(email.equals("jinal@gmail.com") && password.equals("testing")) {
            modelAndView.setViewName("redirect:/signin?message=Login successful!&messageType=success");
        } else {
            modelAndView.setViewName("redirect:/signin?message=Invalid username or password. Please try again.&messageType=error");
        }
        return modelAndView;
    }

}
