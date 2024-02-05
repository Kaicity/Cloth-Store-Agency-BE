package com.example.ctstart.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig  {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Instead of config.addAllowedOrigin("*");
        // Use either of the following approaches:

        // 1. List specific origins explicitly
        // config.setAllowedOrigins(Arrays.asList("http://localhost:4200", "https://your-angular-app-domain"));

        // 2. Use allowedOriginPatterns
        config.setAllowedOriginPatterns(Arrays.asList("http://localhost:4202","http://localhost:4201"));

        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}