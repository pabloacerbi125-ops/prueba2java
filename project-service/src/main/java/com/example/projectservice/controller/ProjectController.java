package com.example.projectservice.controller;

import com.example.projectservice.model.Project;
import com.example.projectservice.repository.ProjectRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    // Controlador de proyectos. Permite listar y crear proyectos.
    // Las rutas se protegen mediante JWT desde la configuración de seguridad.

    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping
    public List<Project> list() {
        return projectRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Project> create(@RequestBody Project project) {
        return ResponseEntity.ok(projectRepository.save(project));
    }

    @GetMapping("/status")
    public ResponseEntity<?> status() {
        return ResponseEntity.ok("Project service protegido y disponible");
    }
}
