package com.example.river_data_nimh.services;

import com.example.river_data_nimh.RiverDataNimhApplication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HealthCheckComponent implements HealthIndicator {

    private final HealthIndicator healthIndicator;

    public HealthCheckComponent(@Qualifier("pingHealthContributor") HealthIndicator healthIndicator) {
        this.healthIndicator = healthIndicator;
    }

    @Override
    public Health health() {
        boolean isHealthy = isApplicationHealthy();
        if (isHealthy) {
            return Health.up().build();
        } else {
            return Health.down().build();
        }
    }

    private boolean isApplicationHealthy() {
        Health health = healthIndicator.health();
        return health.getStatus().equals(Status.UP);
    }

    @Scheduled(fixedRate = 60000)
    public void performHealthCheck() {
        Health applicationHealth = health();
        if (applicationHealth.getStatus().equals(Status.UP)) {
            // Log the health status
            log.info("Application is HEALTHY");
        } else {
            log.info("Application is UNHEALTHY. Restarting...");
            SpringApplication.run(RiverDataNimhApplication.class);
        }
    }
}
