package com.example.river_data_nimh.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {
    @Value("${host.url}")
    private String hostUrl;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/actuator/**")
                .allowedOrigins(hostUrl)
                .allowedMethods("GET");
    }
}
