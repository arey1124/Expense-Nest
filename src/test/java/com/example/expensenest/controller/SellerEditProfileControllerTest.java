package com.example.expensenest.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.example.expensenest.entity.User;
import com.example.expensenest.service.SessionService;
import com.example.expensenest.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.lang.reflect.Field;

class SellerEditProfileControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private SessionService sessionService;

    @InjectMocks
    private SellerEditProfileController sellerEditProfileController;

    @Mock
    private Model model;

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpSession session;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1);
    }

    @Test
    void testConstructor() throws Exception {
        // Create an instance of the controller using the constructor
        SellerEditProfileController controller = new SellerEditProfileController(userService, sessionService);

        // Ensure the constructor initializes the controller correctly with the provided services
        assertNotNull(controller);

        // Access the private fields using reflection
        Field userServiceField = SellerEditProfileController.class.getDeclaredField("userService");
        userServiceField.setAccessible(true);
        UserService userServiceValue = (UserService) userServiceField.get(controller);
        assertEquals(userService, userServiceValue);

        Field sessionServiceField = SellerEditProfileController.class.getDeclaredField("sessionService");
        sessionServiceField.setAccessible(true);
        SessionService sessionServiceValue = (SessionService) sessionServiceField.get(controller);
        assertEquals(sessionService, sessionServiceValue);
    }

    @Test
    void testGetSellerEditProfile() {
        // Mock the necessary services
        when(sessionService.getSession(session)).thenReturn(user);
        when(userService.getUserProfile(1)).thenReturn(user);

        // Call the controller method
        String viewName = sellerEditProfileController.getSellerEditProfile(model, session);

        // Assertions
        assertEquals("/editProfile", viewName);
        verify(model, times(1)).addAttribute(eq("user"), eq(user));

        // Verify that no other interactions occur with the model
        verifyNoMoreInteractions(model);
    }

    @Test
    void testGetSellerEditProfileWithNoProfile() {
        // Mock the necessary services
        when(sessionService.getSession(session)).thenReturn(user);
        when(userService.getUserProfile(1)).thenReturn(null);

        // Call the controller method
        String viewName = sellerEditProfileController.getSellerEditProfile(model, session);

        // Assertions
        assertEquals("/editProfile", viewName);
        verify(model, times(1)).addAttribute(eq("user"), eq(user));

        // Verify that the error message is added to the model
        verify(model, times(1)).addAttribute(eq("errorMessage"), eq("User profile not found."));
    }


    @Test
    void testSaveProfileWithSuccessfulSave() {
        // Mock the necessary service
        when(userService.setUserProfile(user)).thenReturn(true);

        // Call the controller method
        String viewName = sellerEditProfileController.saveProfile(user, model);

        // Assertions
        assertEquals("editProfile", viewName);
        verify(model, times(1)).addAttribute(eq("successMessage"), eq("Profile saved successfully!"));
        verify(model, never()).addAttribute(eq("errorMessage"), anyString());
    }

    @Test
    void testSaveProfileWithUnsuccessfulSave() {
        // Mock the necessary service
        when(userService.setUserProfile(user)).thenReturn(false);

        // Call the controller method
        String viewName = sellerEditProfileController.saveProfile(user, model);

        // Assertions
        assertEquals("editProfile", viewName);
        verify(model, times(1)).addAttribute(eq("errorMessage"), eq("Error occurred while saving the profile."));
        verify(model, never()).addAttribute(eq("successMessage"), anyString());
    }
}
