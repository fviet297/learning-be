package com.learningapp.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;

@Configuration
public class OpenRouterConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Value("${spring.ai.openai.base-url:https://api.openai.com}")
    private String baseUrl;

    @EventListener(ApplicationReadyEvent.class)
    public void logBaseUrl() {
        System.out.println(">>>>> CURRENT OPENAI BASE URL: " + baseUrl);
    }
}
