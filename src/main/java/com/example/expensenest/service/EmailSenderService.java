package com.example.expensenest.service;

public interface EmailSenderService {
    public void sendVerificationEmail(String recipientEmail, String verificationCode);
}
