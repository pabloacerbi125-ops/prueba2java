package com.example.resourceservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/resources")
public class ResourceController {
    // Controlador de recursos. Devuelve datos de ejemplo y está protegido por JWT.

    @GetMapping
    public ResponseEntity<List<Map<String, String>>> listResources() {
        return ResponseEntity.ok(List.of(
                Map.of("id", "resource-1", "name", "Guía de usuarios"),
                Map.of("id", "resource-2", "name", "Documentación técnica")
        ));
    }

    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("Resource Service protegido y listo");
    }
}
