package com.example.river_data_nimh.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConfig {
    @Value("${host.url}")
    private String hostUrl;
}
