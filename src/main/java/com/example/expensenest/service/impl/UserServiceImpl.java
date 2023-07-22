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

    @Override
    public User getUserProfile(int userId) {
        return userRepository.getUserByID(userId);
    }
    @Override
    public Boolean setUserProfile(User userprofile) {
        return userRepository.saveUserProfile(userprofile);
    }
    @Override
    public boolean updateUser(User userprofile) {
        return userRepository.saveUserProfile(userprofile);
    }

    public User findByVerificationCode(String code) {
        return userRepository.findByVerificationCode(code);
    }

    @Override
    public boolean setUserPassword(User user) {
        return userRepository.setUserPassword(user);
    }

    public boolean setPasswordResetVerificationCode(String code, String email) {
        return userRepository.setCode(code,email);
    }

    @Override
    public String addUser(User user) {
        if(userRepository.checkUserExists(user)) {
            return null;
        } else {
            String code = getVerificationCode();
            userRepository.save(user, code);
            return code;
        }

    }

    public String getVerificationCode() {
        String verificationCode = generateUserVerificationCode();
        return verificationCode;
    }

    public boolean verifyUser(String code) {
        return userRepository.verify(code);
    }

    @Override
    public String generateUserVerificationCode() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
