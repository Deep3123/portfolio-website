package com.deeppatel.portfolio.service;

import com.deeppatel.portfolio.dto.ContactRequest;
import com.deeppatel.portfolio.model.ContactMessage;
import com.deeppatel.portfolio.repository.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import java.util.HashMap;

// import org.springframework.mail.SimpleMailMessage;
// import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactService {

    private final ContactMessageRepository repository;
    // private final JavaMailSender mailSender;

    @Value("${spring.mail.username:}")
    private String mailUsername;

    @Value("${RESEND_API_KEY:re_NCEVhzUV_8VkKZ4Q4qYk1miG5jxnXsAeu}")
    private String resendApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public void saveMessage(ContactRequest request) {
        log.info("Received new contact message from {}", request.getEmail());
        ContactMessage message = new ContactMessage();
        message.setName(request.getName());
        message.setEmail(request.getEmail());
        message.setSubject(request.getSubject());
        message.setMessage(request.getMessage());
        repository.save(message);

        // --- OLD JAVAMAILSENDER CONFIGURATION (Commented out) ---
        /*
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
        */

        // --- NEW RESEND HTTP API CONFIGURATION ---
        try {
            if (mailUsername != null && !mailUsername.isEmpty()) {
                String url = "https://api.resend.com/emails";
                
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setBearerAuth(resendApiKey);

                Map<String, Object> body = new HashMap<>();
                // Resend requires sending from 'onboarding@resend.dev' until you verify a custom domain
                body.put("from", "onboarding@resend.dev"); 
                body.put("to", mailUsername);
                body.put("reply_to", request.getEmail());
                body.put("subject", "New Portfolio Contact: " + request.getSubject());
                body.put("html", "<p>You have received a new message from your portfolio website.</p>" +
                                 "<p><strong>Name:</strong> " + request.getName() + "</p>" +
                                 "<p><strong>Email:</strong> " + request.getEmail() + "</p>" +
                                 "<p><strong>Message:</strong><br/>" + request.getMessage().replace("\n", "<br/>") + "</p>");

                HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
                
                String response = restTemplate.postForObject(url, entity, String.class);
                log.info("Resend Email notification sent successfully. Response: {}", response);
            }
        } catch (Exception e) {
            log.error("Failed to send Resend email notification", e);
        }
    }
}
