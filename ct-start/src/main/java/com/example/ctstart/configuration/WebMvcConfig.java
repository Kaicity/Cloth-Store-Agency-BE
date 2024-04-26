package com.example.ctstart.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${clientFE}")
    private String clientFEPort;

    @Value("${adminFE}")
    private String adminFEPort;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println(clientFEPort + "||" + adminFEPort);
        registry.addMapping("/api/**") // Đặt URL pattern của API của bạn ở đây
                .allowedOrigins(clientFEPort, adminFEPort)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Cho phép các phương thức HTTP
                .allowedHeaders("*"); // Cho phép tất cả các header
    }

}
