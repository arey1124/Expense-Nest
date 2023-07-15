package com.example.expensenest.controller;

import com.example.expensenest.entity.User;
import com.example.expensenest.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SetPasswordController {
    private UserService userService;
    public  SetPasswordController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/verify")
    public String verifyUser(@RequestParam("code") String code) {
        // Logic to verify the user based on the verification token
        userService.verifyUser(code);
        User user = userService.findByVerificationCode(code);
        // Redirect to a success page or perform any other necessary actions
        return "redirect:/setPassword?email="+user.getEmail();
    }

    @GetMapping("/setPassword")
    public String getSetPasswordPage(@RequestParam("email") String email,Model model) {
        User user = new User(email,"");
        model.addAttribute("userPassword", user);
        return "setPassword";
    }


    @PostMapping("/user/password")
    public String createPassword(@ModelAttribute("user") User user) {
        System.out.println(user.getPassword());

        boolean valid = userService.setUserPassword(user);
        if (valid) {
            return "redirect:/dashboard";
        } else {
            return "setPassword";
        }
    }



}
