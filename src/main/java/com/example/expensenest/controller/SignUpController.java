package com.example.expensenest.controller;

import com.example.expensenest.entity.User;
import com.example.expensenest.service.EmailSenderService;
import com.example.expensenest.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignUpController {

    private UserService userService;
private  final EmailSenderService emailSenderService;
    public SignUpController(UserService userService, EmailSenderService emailSenderService) {
        this.userService = userService;
        this.emailSenderService = emailSenderService;
    }

    @GetMapping("/signup")
    public String getSignUpPage (Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    @PostMapping("/user/create")
    public String createUser(@ModelAttribute("user") User user, Model model) {
        String code = userService.addUser(user);
        if (code == null) {
            model.addAttribute("userExists",true);
            return "/signup";
        } else {
            emailSenderService.sendVerificationEmail(user.getEmail(),"Please verify your email","Click the following link to verify your email", code);
            model.addAttribute("user",user);
            return "/signup";
        }
    }
}
