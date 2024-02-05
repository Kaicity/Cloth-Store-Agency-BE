package com.example.ctstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example"})
public class CtStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(CtStartApplication.class, args);
    }

}
