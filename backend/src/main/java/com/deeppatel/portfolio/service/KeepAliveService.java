package com.deeppatel.portfolio.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class KeepAliveService {

    private final String RENDER_URL = "https://portfolio-backend-he68.onrender.com/api/analytics/ping";
    private final RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedRate = 840000) // 14 minutes in milliseconds
    public void pingServer() {
        try {
            log.info("Pinging server to keep it alive...");
            String response = restTemplate.getForObject(RENDER_URL, String.class);
            log.info("Keep-alive ping successful: {}", response);
        } catch (Exception e) {
            log.error("Failed to ping server: {}", e.getMessage());
        }
    }
}
