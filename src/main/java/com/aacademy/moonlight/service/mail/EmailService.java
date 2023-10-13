package com.aacademy.moonlight.service.mail;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendEmail(String toEmail, String subject, String message);
}
