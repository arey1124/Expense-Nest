package com.example.expensenest.service;

import com.example.expensenest.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers();

    boolean addUser(User user);
}
