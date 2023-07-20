package com.example.expensenest.repository;

import com.example.expensenest.entity.Category;
import com.example.expensenest.entity.Products;
import com.example.expensenest.enums.CategoryType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CategoryRepository {

    private final JdbcTemplate jdbcTemplate;

    public CategoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Category findCategoryById(int id) {
        String sql = "SELECT * FROM Category WHERE id=" + id;
        return jdbcTemplate.query(sql, new CategoryRowMapper()).get(0);
    }

    public List<Category> getAllCategories() {
        String sql = "SELECT c.id, c.name, c.image, COALESCE(p.totalProducts, 0) as totalProducts\n" +
                "FROM expensenest.Category c\n" +
                "LEFT JOIN (\n" +
                "    SELECT category, count(*) as totalProducts\n" +
                "    FROM expensenest.Products\n" +
                "    GROUP BY category\n" +
                ") p ON c.id = p.category;";
        return jdbcTemplate.query(sql, new CategoryRepository.CategoryRowMapper());
    }

    public boolean addCategory(Category category) {
        String sql = "INSERT INTO expensenest.Category (name, image) VALUES (?, ?)";
        jdbcTemplate.update(sql, category.getName(), category.getImage());
        return true;
    }

    private static class CategoryRowMapper implements RowMapper<Category> {
        @Override
        public Category mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Category category = new Category();
            category.setId(resultSet.getInt("id"));
            category.setName(resultSet.getString("name"));
            category.setImage(resultSet.getString("image"));
            if(resultSet.getInt("totalProducts") > -1) {
                category.setTotalProducts(resultSet.getInt("totalProducts"));
            }
            return category;
        }
    }
}
