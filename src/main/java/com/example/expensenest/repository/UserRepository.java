package com.example.expensenest.repository;

import com.example.expensenest.entity.User;
import com.example.expensenest.entity.UserSignIn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean save(User user) {
        String sql = "INSERT INTO User (name, email, phoneNumber, userType, isVerified) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPhoneNumber(), 1, 0);
        return true;
    }

    public boolean editCustomer(User user) {
        try {
            String UPDATE_PROFILE_QUERY = "UPDATE user SET name = ?, phoneNumber = ? WHERE id = ?";
            Map<String, Object> editedValues = new HashMap<>();
            editedValues.put("id", user.getId());
            editedValues.put("Name", user.getName());
            editedValues.put("phoneNumber", user.getPhoneNumber());

            int rowsAffected = jdbcTemplate.update(UPDATE_PROFILE_QUERY, new Object[]{user.getName(), user.getPhoneNumber(), user.getId()});
            return rowsAffected > 0;
        }catch (Exception ex){
            return false;
        }

    }

    public List<User> findAll() {
        String sql = "SELECT * FROM Users";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }
    public User getUserByID(int userId){
        String sql = "SELECT * FROM user WHERE id = ?";
        RowMapper<User> rowMapper = new UserRowMapper();

        List<User> users = jdbcTemplate.query(sql,new Object[]{  userId}, rowMapper);
        return users.isEmpty() ? null : users.get(0);
    }

    public User getUserByEmailAndPassword(UserSignIn userSignIn) {
        String sql = "SELECT * FROM user WHERE email = ? and password = ?";
        RowMapper<User> rowMapper = new UserRowMapper();

        List<User> users = jdbcTemplate.query(sql,new Object[]{  userSignIn.getEmail(), userSignIn.getPassword()}, rowMapper);
        return users.isEmpty() ? null : users.get(0);
    }

    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            user.setUserType(resultSet.getInt("userType"));
            return user;
        }
    }
}
