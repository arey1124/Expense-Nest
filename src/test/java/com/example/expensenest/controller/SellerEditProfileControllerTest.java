package com.example.expensenest.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.expensenest.entity.User;
import com.example.expensenest.service.SessionService;
import com.example.expensenest.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

class SellerEditProfileControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private SessionService sessionService;

    @Mock
    private Model model;

    @Mock
    private HttpSession session;

    @InjectMocks
    private SellerEditProfileController sellerEditProfileController;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1);
    }

    @Test
    void testGetSellerEditProfileWithProfile() {
        when(sessionService.getSession(session)).thenReturn(user);

        User userProfile = new User();
        userProfile.setId(1);
        when(userService.getUserProfile(1)).thenReturn(userProfile);

        String viewName = sellerEditProfileController.getSellerEditProfile(model, session);

        assertEquals("/editProfile", viewName);
        verify(model, times(1)).addAttribute(eq("user"), eq(userProfile));
    }

    @Test
    void testGetSellerEditProfileWithNoProfile() {
        when(sessionService.getSession(session)).thenReturn(user);
        when(userService.getUserProfile(8)).thenReturn(null);

        String viewName = sellerEditProfileController.getSellerEditProfile(model, session);

        assertEquals("/editProfile", viewName);
        verify(model, times(1)).addAttribute(eq("user"), eq(user));
        verifyNoMoreInteractions(model);
    }

    @Test
    void testSaveProfileWithSuccessfulSave() {
        when(userService.setUserProfile(user)).thenReturn(true);

        String viewName = sellerEditProfileController.saveProfile(user, model);

        assertEquals("editProfile", viewName);
        verify(model, times(1)).addAttribute(eq("successMessage"), eq("Profile saved successfully!"));
        verifyNoMoreInteractions(model);
    }

    @Test
    void testSaveProfileWithUnsuccessfulSave() {
        when(userService.setUserProfile(user)).thenReturn(false);

        String viewName = sellerEditProfileController.saveProfile(user, model);

        assertEquals("editProfile", viewName);
        verify(model, times(1)).addAttribute(eq("errorMessage"), eq("Error occurred while saving the profile."));
        verifyNoMoreInteractions(model);
    }
}
