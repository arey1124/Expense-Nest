package com.example.expensenest.service;

import com.example.expensenest.entity.User;
import com.example.expensenest.entity.UserSignIn;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers();

    boolean addUser(User user);

    boolean editUser(User user);

    User getUserByEmailAndPassword(UserSignIn userSignIn);
}
