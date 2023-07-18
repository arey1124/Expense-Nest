package com.example.expensenest.controller;

import com.example.expensenest.entity.User;
import com.example.expensenest.entity.UserSignIn;
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
public class SignInController {

    private UserService userService;
    private SessionService sessionService;

    public SignInController(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @GetMapping("/signin")
    public String getSignInForm(Model model, HttpServletRequest httpServletRequest, HttpSession session) {
        UserSignIn signIn = new UserSignIn(null,null);
        model.addAttribute("userSignIn", signIn);
        // TODO: redirect to dashboard when user is already logged in
        return "/signin";
    }

    @PostMapping("/signinpost")
    public String checkSignIn(@ModelAttribute("userSignIn") UserSignIn signIn,HttpSession session) {
        User user = userService.getUserByEmailAndPassword(signIn);
        if (user != null) {
            sessionService.createSession(user, session);
            if (user.getUserType() == 1) {
                return "redirect:/dashboard";
            // TODO: redirect to customer dashboard

            } else {
                return "redirect:/signin";
            //  TODO: redirect to seller dashboard
            }
        }
        else {
            return("redirect:/signin?signInMessage=Invalid email or password. Please try again.&isSignInSuccess=error");
        }

    }

    // TODO: Added below controller for verifying logout related functionality, this will be removed later once side-bar navigation panel is implemented
    @GetMapping("/layout")
    public String testPage(Model model) {
        UserSignIn signIn = new UserSignIn(null,null);
        model.addAttribute("userSignIn", signIn);
        return "/layout";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        sessionService.removeSession(session);
        return "redirect:/signin";
    }
}
