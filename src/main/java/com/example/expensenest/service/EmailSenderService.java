package com.example.expensenest.service;

public interface EmailSenderService {
    public void sendVerificationEmail(String recipientEmail, String emailSubject,String emailMessage,String verificationCode);
}
