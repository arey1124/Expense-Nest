package com.example.expensenest.repository;

import com.example.expensenest.entity.UserInsightResponse;
import com.example.expensenest.entity.UserInsights;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class UserInsightsRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserInsightsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserInsightResponse getUserInsightResponse(int sellerId) {

        List<Integer> array = Arrays.asList(
                10, 15, 0, 8, 20, 0, 25, 30, 12, 5, 0, 18, 22, 19, 20, 16, 0, 14, 9, 24, 21, 28, 26, 11, 7, 0, 13, 17, 23, 27
        );

        // Convert the array to JSON format manually
        StringBuilder jsonStringBuilder = new StringBuilder();
        jsonStringBuilder.append("[");
        for (int i = 0; i < array.size(); i++) {
            jsonStringBuilder.append(array.get(i));
            if (i < array.size() - 1) {
                jsonStringBuilder.append(",");
            }
        }
        jsonStringBuilder.append("]");

        // Convert the StringBuilder to a String
        String jsonString = jsonStringBuilder.toString();

        // Print the JSON string representation of the array
        //System.out.println(jsonString);

        UserInsightResponse userInsightResponse = new UserInsightResponse();
        String fetchData = "SELECT    SUM(ri.quantity) as totalSum, COUNT(*) AS total_usage,  c.name FROM receiptitems ri INNER JOIN " +
                "products p ON ri.productId = p.id INNER JOIN category c ON p.category = c.id " +
                "INNER JOIN receipt r ON ri.receiptId = r.id " +
                "WHERE r.sellerId = ? and isArchived = 0 GROUP BY  ri.productId ORDER BY totalSum DESC LIMIT 1;";
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(fetchData, sellerId);


        // Print the fetched data for checking
        for (Map<String, Object> row : resultList) {

            UserInsights userInsights = new UserInsights();
            userInsights.setId(1);
            userInsights.setName((String) row.get("name"));
            BigDecimal totalSum = (BigDecimal) row.get("totalSum");
            double doubleTotalSum = totalSum.doubleValue();
            userInsights.setValue(doubleTotalSum);

            userInsights.setGraphString(jsonString);



            userInsightResponse.getUserInsightsList().add(userInsights);
        }

        String fetchData2 = "SELECT p.id, p.name, SUM(ri.quantity) as totalSum " +
                "FROM expensenest.products p " +
                "JOIN expensenest.receiptItems ri ON p.id = ri.productid " +
                "INNER JOIN receipt r ON ri.receiptId = r.id " +
                "WHERE sellerid = ? AND isArchived = 0 " +
                "GROUP BY p.id, p.name " +
                "ORDER BY totalSum DESC " +
                "LIMIT 1;";

        resultList = jdbcTemplate.queryForList(fetchData2, sellerId);

        for (Map<String, Object> row : resultList) {

            UserInsights userInsights = new UserInsights();
            userInsights.setId(2);
            userInsights.setName((String) row.get("name"));
            BigDecimal totalSum = (BigDecimal) row.get("totalSum");
            double doubleTotalSum = totalSum.doubleValue();
            userInsights.setValue(doubleTotalSum);

            userInsights.setGraphString(jsonString);

            userInsightResponse.getUserInsightsList().add(userInsights);
        }

        String fetchData3  = "SELECT SUM(ri.quantity) as totalSum FROM receiptitems ri INNER JOIN " +
                "receipt r ON ri.receiptId = r.id WHERE sellerid = ? AND IsArchived = 0";

        resultList = jdbcTemplate.queryForList(fetchData3, sellerId);

        for (Map<String, Object> row : resultList) {
            UserInsights userInsights = new UserInsights();
            userInsights.setId(3);
            userInsights.setName((String) row.get(""));
            BigDecimal totalSum = (BigDecimal) row.get("totalSum");
            double doubleTotalSum = totalSum.doubleValue();
            userInsights.setValue(doubleTotalSum);

            userInsights.setGraphString(jsonString);

            userInsightResponse.getUserInsightsList().add(userInsights);

        }

//        RowMapper<User> rowMapper = new UserRepository.UserRowMapper();
//        List<User> users = jdbcTemplate.query(sql,rowMapper);
//        return users.size() > 0 ? users.get(0) : null;

        return userInsightResponse;
    }
}
