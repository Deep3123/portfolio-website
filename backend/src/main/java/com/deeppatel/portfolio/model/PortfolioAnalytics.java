package com.deeppatel.portfolio.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "portfolio_analytics")
@Data
@NoArgsConstructor
public class PortfolioAnalytics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userAgent;
    private String ipAddress;
    private String referrer;

    private LocalDateTime visitTime = LocalDateTime.now();
}
