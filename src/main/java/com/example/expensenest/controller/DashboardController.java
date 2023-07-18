package com.example.expensenest.controller;

import com.example.expensenest.entity.Invoice;
import com.example.expensenest.entity.User;
import com.example.expensenest.service.InvoiceService;
import com.example.expensenest.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class    DashboardController {

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
        model.addAttribute("user", userSession);
        model.addAttribute("archivedState", false);
        return "allInvoices";
    }

    @GetMapping("/archived")
    public String getArchivedInvoices (HttpServletRequest request, HttpSession session, Model model) {
        User userSession = sessionService.getSession(session);
        model.addAttribute("invoices", invoiceService.getUserInvoices(userSession.getId()));
        model.addAttribute("user", userSession);
        model.addAttribute("archivedState", true);
        return "allInvoices";
    }

    @PostMapping("/invoices")
    public String searchInvoices (HttpServletRequest request, HttpSession session, Model model, @ModelAttribute("queryString") String queryString) {
        User userSession = sessionService.getSession(session);
        model.addAttribute("invoices", invoiceService.getFilteredInvoices(userSession.getId(), queryString));
        model.addAttribute("user", userSession);
        return "allInvoices";
    }

    @PostMapping("/archive/{invoiceId}")
    public String archiveInvoices (@PathVariable(value="invoiceId") String invoiceId, @ModelAttribute("archivedReason") String archivedReason) {
        System.out.println(archivedReason+ " : " + invoiceId);
        invoiceService.updateInvoiceArchiveData(Integer.valueOf(invoiceId), true, archivedReason);
        return "redirect:/invoices";
    }
}
