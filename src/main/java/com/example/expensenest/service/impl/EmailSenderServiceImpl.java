package com.example.expensenest.service.impl;
import com.example.expensenest.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    JavaMailSender javaMailSender;

    @Autowired
    public EmailSenderServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Override
    public void sendVerificationEmail(String recipientEmail, String emailSubject,String emailMessage, String verificationCode) {
        SimpleMailMessage message = new SimpleMailMessage();
try {

    message.setTo(recipientEmail);
    message.setSubject(emailSubject);
    message.setText(emailMessage+": " +"http://localhost:8080/verify?code=" + verificationCode);
    javaMailSender.send(message);
} catch (MailException e) {
    e.printStackTrace();
}
    }


}