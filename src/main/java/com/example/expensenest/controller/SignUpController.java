package com.example.expensenest.controller;

import com.example.expensenest.entity.User;
import com.example.expensenest.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.HttpCookie;

@Controller
public class SignUpController {

    private UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String getSignUpPage (HttpServletRequest request, HttpSession session, Model model) {
        User user = new User();
        model.addAttribute("user", user);
        if(session.getAttribute("loggedInUser") != null) {
            // If user is logged in and session exists for the user then redirect to dashboard
            System.out.println(((User) session.getAttribute("loggedInUser")).getName());
        }
        return "signup";
    }

    @PostMapping("/user/create")
    public String createUser(HttpServletRequest request, HttpSession session, @ModelAttribute("user") User user) {
        userService.addUser(user);
        session.setAttribute("loggedInUser", user);
        // TODO: Need to redirect to password setup page
        return "redirect:/signup";
    }
}
