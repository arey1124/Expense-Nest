package com.example.expensenest.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface DashboardService {
    String getUserName(int userId);
    List<Map<String, Object>> getInvoiceData(int userId);
    List<Map<String, Object>> getStatsData();
}
