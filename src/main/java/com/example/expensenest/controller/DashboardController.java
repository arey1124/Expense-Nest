package com.example.expensenest.controller;

import com.example.expensenest.service.DashboardService;
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
    private DashboardService dashboardService;

    public DashboardController(InvoiceService invoiceService, DashboardService dashboardService) {
        this.invoiceService = invoiceService;
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard")
    public String getUserDashboard (HttpServletRequest request, HttpSession session, Model model) {
        model.addAttribute("invoiceData", dashboardService.getInvoiceData(2));
        model.addAttribute("userData", dashboardService.getUserName(2));
        model.addAttribute("statsData", dashboardService.getStatsData().get(0));
        return "dashboard";
    }

    @GetMapping("/invoices")
    public String getAllInvoices (HttpServletRequest request, HttpSession session, Model model) {
        model.addAttribute("invoices", invoiceService.getUserInvoices(2));
        return "allInvoices";
    }
}
