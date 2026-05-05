package com.example.collabservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/collabs")
public class CollabController {
    // Controlador de colaboración. Devuelve información de colaboración y está protegido por JWT.

    @GetMapping
    public ResponseEntity<List<Map<String, String>>> listCollaborations() {
        return ResponseEntity.ok(List.of(
                Map.of("id", "collab-1", "topic", "Revisión de proyecto"),
                Map.of("id", "collab-2", "topic", "Sesión de diseño")
        ));
    }

    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("Collab Service protegido y disponible");
    }
}
