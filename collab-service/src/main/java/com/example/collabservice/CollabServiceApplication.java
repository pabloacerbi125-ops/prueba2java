package com.example.collabservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// Servicio de colaboración. Proporciona datos de colaboración y está protegido por JWT.
@SpringBootApplication
@EnableDiscoveryClient
public class CollabServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CollabServiceApplication.class, args);
    }
}
