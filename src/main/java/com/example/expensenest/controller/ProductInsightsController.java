package com.example.expensenest.controller;

import com.example.expensenest.entity.User;
import com.example.expensenest.service.SessionService;
import com.example.expensenest.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductInsightsController {
    private SessionService sessionService;
    private UserService userService;

    public ProductInsightsController(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @GetMapping("/productInsights")
    public String getSellerEditProfile(Model model, HttpServletRequest httpServletRequest, HttpSession session) {
        User userSession = sessionService.getSession(session);
        User profile = userService.getUserProfile(userSession.getId());
        model.addAttribute("user", profile);
        return "/productInsights";
    }

}
