package com.example.projectservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// Microservicio de proyectos. Opera con datos de proyectos y está protegido por JWT.
@SpringBootApplication
@EnableDiscoveryClient
public class ProjectServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectServiceApplication.class, args);
    }
}
