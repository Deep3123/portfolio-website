package com.deeppatel.portfolio.repository;

import com.deeppatel.portfolio.model.PortfolioAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyticsRepository extends JpaRepository<PortfolioAnalytics, Long> {
}
