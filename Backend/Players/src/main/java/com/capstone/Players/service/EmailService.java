
package com.capstone.Players.service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

//    public EmailService(JavaMailSender mailSender) {
//        this.mailSender = mailSender;
//    }

    public void sendWelcomeEmail(String toEmail, String userId) throws MessagingException, IOException {
        // Load the HTML template
        Path templatePath = new ClassPathResource("templates/email-template/index.html").getFile().toPath();
        String htmlContent = new String(Files.readAllBytes(templatePath));

        htmlContent = htmlContent.replace("{{USERID}}", userId);

        // Set up the email
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(toEmail);
        helper.setSubject("Welcome to CricPlayy!!!");
        helper.setText(htmlContent, true); // Set 'true' for HTML content

        // Send the email
        mailSender.send(message);
    }
}
