package com.example.expensenest.service.impl;

import com.example.expensenest.entity.DataPoint;
import com.example.expensenest.service.DashboardService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        RowMapper<String> userRowMapper = (rs, rowNum) -> {
            String username = rs.getString("name");
            return username;
        };

        // Fetch data
        String userName = jdbcTemplate.query("SELECT SUBSTRING(name, 1, 1) AS name FROM User WHERE id =" + userId, userRowMapper).get(0);

        return userName;
    }

    @Override
    public List<Map<String, Object>> getInvoiceData(int userId) {
        Map<String, Object> invoiceData = new HashMap<>();

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

    @Override
    public List<DataPoint> getChartData() {
        RowMapper<DataPoint> dataPointRowMapper = (rs, rowNum) -> {
            String label = rs.getString("price");
            double value = rs.getDouble("quantity");
            return new DataPoint(label, value);
        };

        // Fetch data
        List<DataPoint> chartData = jdbcTemplate.query("SELECT t1.price, t2.quantity FROM products t1 JOIN receiptitems t2 ON t1.id = t2.productId LIMIT 5", dataPointRowMapper);

        return chartData;
    }

        @Override
        public List<DataPoint> getBarData() {
            RowMapper<DataPoint> barRowMapper = (rs, rowNum) -> {
                double totalAmount = rs.getDouble("totalAmount");
                Date timeStamp = rs.getDate("dateOfPurchase");
                return new DataPoint(totalAmount, timeStamp);
            };

            // Fetch data
            List<DataPoint> barData = jdbcTemplate.query("SELECT totalAmount, dateOfPurchase FROM receipt LIMIT 5", barRowMapper);

            return barData;
    }
    @Override
    public List<DataPoint> getPieData() {
        RowMapper<DataPoint> pieRowMapper = (rs, rowNum) -> {
            String name = rs.getString("name");
            Integer sumAmount = rs.getInt("totalAmount");
            return new DataPoint(name, sumAmount);
        };

        // Fetch data
        List<DataPoint> pieData = jdbcTemplate.query("SELECT c.name, SUM(r.totalAmount) AS totalAmount FROM company c JOIN receipt r ON c.id = r.sellerId GROUP BY c.name LIMIT 5", pieRowMapper);

        return pieData;
    }

}
