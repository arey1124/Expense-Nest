package com.example.expensenest.controller;

import com.example.expensenest.entity.User;
import com.example.expensenest.service.SessionService;
import com.example.expensenest.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SellerEditProfileController {
    private SessionService sessionService;
    private UserService userService;

    public SellerEditProfileController(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @GetMapping("/editSeller")
    public String getSellerEditProfile(Model model, HttpServletRequest httpServletRequest, HttpSession session) {
        User userSession = sessionService.getSession(session);
        User profile = userService.getUserProfile(userSession.getId());
        model.addAttribute("user", profile);
        return "/editProfile";
    }

    @PostMapping("/saveSeller")
    public String saveProfile(@ModelAttribute("user") User user, Model model) {

        boolean saved = userService.setUserProfile(user);
        if (saved) {
            model.addAttribute("successMessage", "Profile saved successfully!");
        } else {
            model.addAttribute("errorMessage", "Error occurred while saving the profile.");
        }
        return "editProfile";
    }
}
