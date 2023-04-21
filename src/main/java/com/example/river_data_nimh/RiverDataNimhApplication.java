package com.example.river_data_nimh;

import com.example.river_data_nimh.services.ScraperService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class RiverDataNimhApplication implements CommandLineRunner {
    private final ScraperService scraperService;
    public static void main(String[] args) {
        SpringApplication.run(RiverDataNimhApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        scraperService.scrapeAndSave();
    }
}
