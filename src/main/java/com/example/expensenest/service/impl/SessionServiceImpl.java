package com.example.expensenest.service.impl;

import com.example.expensenest.entity.User;
import com.example.expensenest.service.SessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {

    private static final String Session_Key = "userSignIn";

    @Override
    public void createSession(User user, HttpSession session) {
        session.setAttribute(Session_Key, user);
    }

    @Override
    public void removeSession(HttpSession session) {
        session.removeAttribute(Session_Key);
    }

    @Override
    public User getSession( HttpSession session) {
        User userSession = (User) session.getAttribute(Session_Key);
        return userSession;
    }

}
