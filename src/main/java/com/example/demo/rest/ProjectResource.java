package com.example.demo.rest;

import com.example.demo.entity.Project;
import com.example.demo.serviceimp.ProjectService;
import com.example.demo.serviceimp.dto.ProjectDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProjectResource implements ProjectAPI {

    private final ProjectService projectService;

    @Override
    public ResponseEntity<Project> createProject(ProjectDTO projectDTO) {
        Project project = projectService.createProject(projectDTO);
        return ResponseEntity.created(URI.create("/api/projects" + project.getProjectId())).body(project);
    }

    @Override
    public ResponseEntity<List<Project>> getAllProject() {
        return ResponseEntity.ok(projectService.getAllProject());
    }

    @Override
    public ResponseEntity<Optional<Project>> getProjectById(Long projectId) {
        return ResponseEntity.ok(projectService.getProjectById(projectId));
    }

    @Override
    public ResponseEntity<Project> updateProject(Long projectId, ProjectDTO projectDTO) {
        Project project = projectService.updateProject(projectId, projectDTO);
        return ResponseEntity.created(URI.create("/api/projects" + project.getProjectId())).body(project);
    }

    @Override
    public ResponseEntity<Void> deleteProject(Long projectId) {
        projectService.deleteProjectById(projectId);
        return ResponseEntity.noContent().build();
    }
}
