package com.example.expensenest.interceptor;
import com.example.expensenest.entity.User;
import com.example.expensenest.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ActiveSessionManager implements HandlerInterceptor {
    private SessionService sessionService;

    public ActiveSessionManager(SessionService sessionService){
        this.sessionService = sessionService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        // If the user is signed in, and is trying to access any of the pages (sign up, sign in, and forgot password),
        // then redirect the user to the dashboard page. else continue to the page user is trying to access.
        HttpSession session = request.getSession();
        User userSession = sessionService.getSession(session);
        if (userSession != null) {
            response.sendRedirect("/layout");
            return false;
        }
        return true;
    }
}
