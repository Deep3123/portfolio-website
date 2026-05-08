package com.deeppatel.portfolio.controller;

import com.deeppatel.portfolio.model.PortfolioAnalytics;
import com.deeppatel.portfolio.repository.AnalyticsRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsRepository analyticsRepository;

    @PostMapping("/visit")
    public ResponseEntity<Void> recordVisit(HttpServletRequest request) {
        PortfolioAnalytics analytics = new PortfolioAnalytics();
        analytics.setIpAddress(request.getRemoteAddr());
        analytics.setUserAgent(request.getHeader("User-Agent"));
        analytics.setReferrer(request.getHeader("Referer"));
        
        analyticsRepository.save(analytics);
        return ResponseEntity.ok().build();
    }
}
