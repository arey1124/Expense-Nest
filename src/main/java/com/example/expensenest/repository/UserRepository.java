package com.example.expensenest.repository;

import com.example.expensenest.entity.User;
import com.example.expensenest.entity.UserSignIn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

    public boolean edit(User user) {
        String sql = "UPDATE user\n" +
                "SET\n" +
                "name = ?,\n" +
                "email = ?,\n" +
                "phoneNumber = <?,\n" +
                "isVerified = ?,\n" +
                "userType = ?,\n" +
                "WHERE id = ?;";
        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPhoneNumber(), 1, 1, user.getId());
        return true;
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM Users";
        return jdbcTemplate.query(sql, new UserRowMapper());
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
