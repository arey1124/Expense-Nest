package com.example.expensenest.controller;

import com.example.expensenest.entity.User;
import com.example.expensenest.service.SessionService;
import com.example.expensenest.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SellerEditProfileController {
    private static final Logger logger = LogManager.getLogger(SellerEditProfileController.class);

    private SessionService sessionService;
    private UserService userService;

    public SellerEditProfileController(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @GetMapping("/editSeller")
    public String getSellerEditProfile(Model model, HttpServletRequest httpServletRequest, HttpSession session) {
        logger.info("Handling GET request for /editSeller");
        User userSession = sessionService.getSession(session);
        model.addAttribute("user", userSession);

        // Log user details
        logger.debug("User session details: {}", userSession);

        // Log session attributes
        logger.debug("Session attributes: {}", session.getAttributeNames());

        User profile = userService.getUserProfile(userSession.getId());
        if (profile != null) {
            logger.info("Retrieved user profile: {}", profile);
            model.addAttribute("user", profile);
        } else {
            logger.warn("Failed to retrieve user profile for ID: {}", userSession.getId());
        }
        return "/editProfile";
    }

    @PostMapping("/saveSeller")
    public String saveProfile(@ModelAttribute("user") User user, Model model) {
        logger.info("Handling POST request for /saveSeller");

        // Log user details from the form
        logger.debug("User details from form: {}", user);

        boolean saved = userService.setUserProfile(user);
        if (saved) {
            logger.info("User profile saved successfully: {}", user);
            model.addAttribute("successMessage", "Profile saved successfully!");
        } else {
            logger.error("Error occurred while saving the profile: {}", user);
            model.addAttribute("errorMessage", "Error occurred while saving the profile.");
        }
        return "editProfile";
    }
}
