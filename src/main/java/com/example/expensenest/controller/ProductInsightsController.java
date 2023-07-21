package com.example.expensenest.controller;

import com.example.expensenest.entity.User;
import com.example.expensenest.entity.UserInsightResponse;
import com.example.expensenest.service.SessionService;
import com.example.expensenest.service.UserInsightsService;
import com.example.expensenest.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductInsightsController {
    private SessionService sessionService;
    private UserService userService;
    private UserInsightsService userInsightsService;

    public ProductInsightsController(UserService userService, SessionService sessionService, UserInsightsService userInsightsService) {
        this.userService = userService;
        this.sessionService = sessionService;
        this.userInsightsService = userInsightsService;
    }

    @GetMapping("/productInsights")
    public String getSellerEditProfile() {
        return "/productInsights";
    }

    @GetMapping("/seller-chart-data")
    @ResponseBody
    public UserInsightResponse getProductInsights(HttpServletRequest request){
        HttpSession session = request.getSession();
        User userSession = sessionService.getSession(session);
        User profile = userService.getUserProfile(userSession.getId());
        var response =  userInsightsService.getUserInsightResponse(profile.getId());
        return response;
    }

}
