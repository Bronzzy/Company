package com.example.demo.rest;

import com.example.demo.entity.Project;
import com.example.demo.serviceimp.dto.ProjectDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/projects")
public interface ProjectAPI {

    @PostMapping
    ResponseEntity<Project> createProject(ProjectDTO projectDTO);

    @GetMapping
    ResponseEntity<List<Project>> getAllProject();

    @GetMapping("/{projectId}")
    ResponseEntity<Optional<Project>> getProjectById(@PathVariable("projectId") Long projectId);

    @PutMapping("/{projectId}")
    ResponseEntity<Project> updateProject(@PathVariable("projectId") Long projectId,
                                          @RequestBody ProjectDTO projectDTO);

    @DeleteMapping("/{projectId}")
    ResponseEntity<Void> deleteProject(@PathVariable("projectId") Long projectId);
}
