package com.example.expensenest.service;

import com.example.expensenest.entity.User;
import com.example.expensenest.entity.UserSignIn;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers();

    String addUser(User user);

    boolean verifyUser(String code);

    User getUserByEmailAndPassword(UserSignIn userSignIn);

    User getUserProfile(int sellerUserId);
    Boolean setUserProfile(User userprofile);

    User findByVerificationCode(String code);
    boolean setUserPassword(User user);

    public String generateUserVerificationCode();
    public boolean setPasswordResetVerificationCode(String code, String email);
}
