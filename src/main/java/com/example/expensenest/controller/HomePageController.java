package com.example.expensenest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    public HomePageController() {

    }

    @GetMapping("/homepage")
    public String getHomePage (Model model) {
        model.addAttribute("homepage", null);
        return "homepage";
    }
}
