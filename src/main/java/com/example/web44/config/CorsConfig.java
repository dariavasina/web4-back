package com.example.web44.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/v1/**") // Путь к API
                .allowedOrigins("http://localhost:3000") // Разрешенный домен
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Разрешенные методы
                .allowedHeaders("Origin", "Content-Type", "Accept"); // Разрешенные заголовки
    }
}
