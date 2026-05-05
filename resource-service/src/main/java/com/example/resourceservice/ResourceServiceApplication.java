package com.example.resourceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// Servicio de recursos de ejemplo. Este servicio retorna datos de recursos protegidos.
@SpringBootApplication
@EnableDiscoveryClient
public class ResourceServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResourceServiceApplication.class, args);
    }
}
