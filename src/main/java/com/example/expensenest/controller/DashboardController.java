package com.example.expensenest.controller;

import com.example.expensenest.entity.User;
import com.example.expensenest.service.InvoiceService;
import com.example.expensenest.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private InvoiceService invoiceService;
    private SessionService sessionService;

    public DashboardController(InvoiceService invoiceService, SessionService sessionService) {
        this.invoiceService = invoiceService;
        this.sessionService = sessionService;
    }

    @GetMapping("/dashboard")
    public String getUserDashboard (HttpServletRequest request, HttpSession session, Model model) {
        return "dashboard";
    }

    @GetMapping("/invoices")
    public String getAllInvoices (HttpServletRequest request, HttpSession session, Model model) {
        User userSession = sessionService.getSession(session);
        model.addAttribute("invoices", invoiceService.getUserInvoices(userSession.getId()));
        System.out.println(userSession.getId());
        return "allInvoices";
    }
}
