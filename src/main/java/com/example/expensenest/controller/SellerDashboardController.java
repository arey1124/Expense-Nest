package com.example.expensenest.controller;

import com.example.expensenest.service.InvoiceService;
import com.example.expensenest.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SellerDashboardController {

    private InvoiceService invoiceService;
    private SessionService sessionService;

    public SellerDashboardController(InvoiceService invoiceService, SessionService sessionService) {
        this.invoiceService = invoiceService;
        this.sessionService = sessionService;
    }

    @GetMapping("/seller/dashboard")
    public String getSellerDashboard (HttpServletRequest request, HttpSession session, Model model) {
        return "sellerDashboard";
    }

    @GetMapping("/manage/category")
    public String getCategories (HttpServletRequest request, HttpSession session, Model model) {
        return "sellerDashboard";
    }

    @GetMapping("/category/{categoryId}")
    public String getProductsByCategory (HttpServletRequest request, HttpSession session, Model model, @PathVariable(value="categoryId") String categoryId) {
        return "sellerDashboard";
    }

    @GetMapping("/add/product")
    public String addNewProduct (HttpServletRequest request, HttpSession session, Model model) {
        return "sellerDashboard";
    }
}
