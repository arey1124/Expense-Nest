package com.example.expensenest.service.impl;

import com.example.expensenest.service.DashboardService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService {
    private final JdbcTemplate jdbcTemplate;

    public DashboardServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String getUserName(int userId) {
        // Define RowMapper for User
        RowMapper<String> userRowMapper = (rs, rowNum) -> {
            String username = rs.getString("name");
            return username;
        };

        // Fetch data
        String userName = jdbcTemplate.query("SELECT * FROM User WHERE id = ?", userRowMapper, userId).get(0);

        return userName;
    }

    @Override
    public List<Map<String, Object>> getInvoiceData(int userId) {
        Map<String, Object> invoiceData = new HashMap<>();

        // Define RowMapper for Company
        RowMapper<Map<String, Object>> invoiceRowMapper = (rs, rowNum) -> {
            Map<String, Object> invoice = new HashMap<>();
            invoice.put("id", rs.getInt("id"));
            invoice.put("totalAmount", rs.getInt("totalAmount"));
            invoice.put("companyName", rs.getString("name"));
            return invoice;
        };

        // Fetch data
        List<Map<String, Object>> invoice = jdbcTemplate.query("SELECT r.id, r.totalAmount, c.name FROM receipt r INNER JOIN company c ON r.sellerId = c.id WHERE r.userId = ?", invoiceRowMapper, userId);

//        invoiceData.put("invoice", invoice);

        return invoice;
    }


    @Override
    public List<Map<String, Object>> getStatsData() {
        Map<String, Object> statsData = new HashMap<>();

        // Define RowMapper for Company
        RowMapper<Map<String, Object>> statsRowMapper = (rs, rowNum) -> {
            Map<String, Object> stats = new HashMap<>();
            stats.put("itemsBought", rs.getInt("quantity"));
            stats.put("totalExpense", rs.getInt("totalExpense"));
            stats.put("maxExpense", rs.getString("maxExpense"));
            return stats;
        };

        // Fetch data
        List<Map<String, Object>> stats = jdbcTemplate.query("SELECT sum(ri.quantity) as quantity, sum(r.totalAmount) as totalExpense, max(r.totalAmount) as maxExpense FROM receipt r INNER JOIN receiptitems ri ON r.id = ri.receiptId", statsRowMapper);

        return stats;
    }

}
