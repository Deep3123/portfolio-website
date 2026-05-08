package com.deeppatel.portfolio.service;

import com.deeppatel.portfolio.dto.ContactRequest;
import com.deeppatel.portfolio.model.ContactMessage;
import com.deeppatel.portfolio.repository.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactService {

    private final ContactMessageRepository repository;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username:}")
    private String mailUsername;

    public void saveMessage(ContactRequest request) {
        log.info("Received new contact message from {}", request.getEmail());
        ContactMessage message = new ContactMessage();
        message.setName(request.getName());
        message.setEmail(request.getEmail());
        message.setSubject(request.getSubject());
        message.setMessage(request.getMessage());
        repository.save(message);

        // Send email notification using JavaMailSender
        try {
            if (mailUsername != null && !mailUsername.isEmpty() && !mailUsername.equals("your-email@gmail.com")) {
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setTo(mailUsername); // Send to yourself
                mailMessage.setReplyTo(request.getEmail());
                mailMessage.setSubject("New Portfolio Contact: " + request.getSubject());
                mailMessage.setText(
                        "You have received a new message from your portfolio website.\n\n" +
                                "Name: " + request.getName() + "\n" +
                                "Email: " + request.getEmail() + "\n" +
                                "Subject: " + request.getSubject() + "\n\n" +
                                "Message:\n" + request.getMessage());
                mailSender.send(mailMessage);
                log.info("Email notification sent successfully.");
            } else {
                log.warn("SMTP username not configured or is default. Email notification skipped.");
            }
        } catch (Exception e) {
            log.error("Failed to send email notification", e);
        }
    }
}
