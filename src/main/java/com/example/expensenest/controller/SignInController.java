package com.example.expensenest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignInController {

    @GetMapping("/signin")
    public ModelAndView getSignInForm(String signInMessage, String isSignInSuccess) {
        ModelAndView signInMV = new ModelAndView("signin");
        signInMV.addObject("signInMessage", signInMessage);
        signInMV.addObject("isSignInSuccess", isSignInSuccess);
        return signInMV;
    }

    @PostMapping("/signinpost")
    public ModelAndView checkSignIn(String email, String password) {
        ModelAndView checkSignInMV = new ModelAndView();
        if(email.equals("jinal@gmail.com") && password.equals("testing")) {
            checkSignInMV.setViewName("redirect:/signin?signInMessage=Login successful!&isSignInSuccess=success");
        } else {
            checkSignInMV.setViewName("redirect:/signin?signInMessage=Invalid username or password. Please try again.&isSignInSuccess=error");
        }
        return checkSignInMV;
    }

}
