package com.example.river_data_nimh.web;

import com.example.river_data_nimh.services.ScraperService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ScraperController {
    private final ScraperService scraperService;

    public ScraperController(ScraperService scraperService) {
        this.scraperService = scraperService;
    }

    @PostMapping("/scrape")
    public ResponseEntity<String> scrape() throws IOException {
        scraperService.scrapeAndSave();
        return ResponseEntity.ok("Scraper started successfully!");
    }
}
