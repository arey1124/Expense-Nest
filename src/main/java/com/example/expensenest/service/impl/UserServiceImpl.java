package com.example.expensenest.service.impl;

import com.example.expensenest.entity.User;
import com.example.expensenest.entity.UserSignIn;
import com.example.expensenest.repository.UserRepository;
import com.example.expensenest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public boolean addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean editUser(User user) {
        return userRepository.edit(user);
    }
}
