package com.example.finance;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    /** value coming from application.properties */
    @Value("${spring.security.user.password}")
    private String adminPassword;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /* ---- temporary sanity-check; delete when you’re done ---- */
    @PostConstruct
    void showAdminPassword() {
        System.out.println(">>> loaded admin pwd property = " + adminPassword);
    }
}