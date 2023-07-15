package com.example.expensenest.service.impl;

import com.example.expensenest.entity.User;
import com.example.expensenest.entity.UserSignIn;
import com.example.expensenest.repository.UserRepository;
import com.example.expensenest.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByEmailAndPassword(UserSignIn userSignIn) {
        return userRepository.getUserByEmailAndPassword(userSignIn);
    }

    public User findByVerificationCode(String code) {
        return userRepository.findByVerificationCode(code);
    }

    @Override
    public boolean setUserPassword(User user) {
        return userRepository.setUserPassword(user);
    }
    @Override
    public String addUser(User user) {
        String verificationCode = generateUserVerificationCode();
        userRepository.save(user, verificationCode);
        return verificationCode;
    }

    public boolean verifyUser(String code) {
        return userRepository.verify(code);
    }

    public static String generateUserVerificationCode() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
