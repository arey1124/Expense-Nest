package com.example.expensenest.controller;

import com.example.expensenest.entity.Report;
import com.example.expensenest.entity.SalesReport;
import com.example.expensenest.entity.User;
import com.example.expensenest.repository.ReportRepository;
import com.example.expensenest.service.ReportService;
import com.example.expensenest.service.SessionService;
import com.example.expensenest.service.UserService;
import com.example.expensenest.service.impl.ReportServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
public class ReportController {
    private SessionService sessionService;
    private UserService userService;

    private ReportService reportService;
    public ReportController(UserService userService, SessionService sessionService, ReportService reportService) {
        this.userService = userService;
        this.sessionService = sessionService;
        this.reportService = reportService;
    }

    @GetMapping("/reports")
    public String getReportsPage(Model model, HttpServletRequest httpServletRequest, HttpSession session) {
        User userSession = sessionService.getSession(session);
        User userInfo = userService.getUserProfile(userSession.getId());
        System.out.println((new Date()).toString());
        LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Report report = new Report(formattedDate,formattedDate);
        model.addAttribute("report",report);
        model.addAttribute("userInfo",userInfo);
        return "/reports";
    }

    @PostMapping("/generateReport")
    public String generateReport(@ModelAttribute("report") Report report,Model model, HttpSession session) {
        User userSession = sessionService.getSession(session);
        User userInfo = userService.getUserProfile(userSession.getId());
        System.out.println(report);

        model.addAttribute("userInfo",userInfo);
        model.addAttribute("reportData",getReportData());
        return "salesReport";
    }

    public List<SalesReport> getReportData() {
        return reportService.getSalesReportData(1);
    }
}
