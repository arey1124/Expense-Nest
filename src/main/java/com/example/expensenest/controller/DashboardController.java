package com.example.expensenest.controller;

import com.example.expensenest.service.InvoiceService;
import com.example.expensenest.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private InvoiceService invoiceService;

    public DashboardController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoices")
    public String getAllInvoices (HttpServletRequest request, HttpSession session, Model model) {
        model.addAttribute("invoices", invoiceService.getUserInvoices(2));
        return "allInvoices";
    }
}
