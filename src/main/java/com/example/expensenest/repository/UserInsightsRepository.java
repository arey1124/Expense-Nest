package com.example.expensenest.repository;

import com.example.expensenest.entity.UserInsightResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserInsightsRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserInsightsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserInsightResponse getUserInsightResponse(int sellerId) {
        UserInsightResponse userInsightResponse = new UserInsightResponse();
        String fetchData = "SELECT    COUNT(*) AS total_usage,  c.name FROM receiptitems ri INNER JOIN\n" +
             "  products p ON ri.productId = p.id INNER JOIN category c ON p.category = c.id INNER JOIN receipt r ON ri.receiptId = r.id\n" +
                " WHERE r.sellerId = ? GROUP BY  ri.productId ORDER BY total_usage DESC LIMIT 1;";
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(fetchData, sellerId);

        // Print the fetched data for checking
        for (Map<String, Object> row : resultList) {
            Long totalUsage = (Long) row.get("total_usage");
            String categoryName = (String) row.get("name");
            System.out.println("Total Usage: " + totalUsage + ", Category Name: " + categoryName);
        }
//        RowMapper<User> rowMapper = new UserRepository.UserRowMapper();
//        List<User> users = jdbcTemplate.query(sql,rowMapper);
//        return users.size() > 0 ? users.get(0) : null;

        return userInsightResponse;
    }
}
