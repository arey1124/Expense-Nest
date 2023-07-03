package com.example.expensenest.repository;

import com.example.expensenest.entity.Category;
import com.example.expensenest.entity.Products;
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

    private static class CategoryRowMapper implements RowMapper<Category> {
        @Override
        public Category mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Category category = new Category();
            category.setId(resultSet.getInt("id"));
            category.setName(resultSet.getString("name"));
            category.setImage(resultSet.getString("image"));
            return category;
        }
    }
}
