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

    public boolean save(User user, String verificationCode) {
        String sql = "INSERT INTO User (name, email, phoneNumber, userType, isVerified, verificationCode) VALUES (?, ?, ?, ?, ?,?)";
        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPhoneNumber(), 1, 0,verificationCode);
        return true;
    }


    public User findByVerificationCode(String code) {
        String sql = "Select * from user where verificationCode = '" +code + "'";
        RowMapper<User> rowMapper = new UserRowMapper();
        List<User> users = jdbcTemplate.query(sql,rowMapper);
        return users.size() > 0 ? users.get(0) : null;
    }

    public boolean setCode(String code, String email) {
        String sql = "UPDATE User SET verificationCode='" + code + "' where email='"+email+"'";
        jdbcTemplate.update(sql);
        return true;
    }
    public boolean verify(String code) {
        String sql = "UPDATE User SET isVerified=1 where verificationCode='"+code+"'";
        jdbcTemplate.update(sql);
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

    public boolean checkUserExists(User user) {
        String sql = "Select * from User where email = ?";
        RowMapper<User> rowMapper = new UserRowMapper();
        List<User> users = jdbcTemplate.query(sql,new Object[]{  user.getEmail()}, rowMapper);
        return !users.isEmpty();
    }

    public boolean setUserPassword(User user) {
        String sql = "UPDATE User SET password = ? where email = ?";
        int rows  = jdbcTemplate.update(sql, user.getPassword(), user.getEmail());
        return rows == 1;
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
