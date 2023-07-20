package com.example.expensenest.controller;

import com.example.expensenest.entity.User;
import com.example.expensenest.service.EmailSenderService;
import com.example.expensenest.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SellerSignUpController {

    private UserService userService;
    private  final EmailSenderService emailSenderService;

    public SellerSignUpController(UserService userService, EmailSenderService emailSenderService) {
        this.userService = userService;
        this.emailSenderService = emailSenderService;
    }

    @GetMapping("/signUpSeller")
    public String getSignInForm(Model model) {
        User signUp = new User();
        model.addAttribute("sellerSignUp", signUp);
        return "/signUpSeller";
    }

    @PostMapping("/sellersigninpost")
    public String checkSignIn(@ModelAttribute("sellerSignUp") User signUp) {
        String code = userService.addSeller(signUp);
        if (code == null) {
            return "redirect:/signin";
        } else {
            emailSenderService.sendVerificationEmail(signUp.getEmail(), code);
            return "redirect:/signUpSeller";
        }
    }
}
