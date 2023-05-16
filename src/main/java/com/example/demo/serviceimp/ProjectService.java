package com.example.demo.serviceimp;

import com.example.demo.mapper.ProjectMapper;
import com.example.demo.serviceimp.dto.ProjectDTO;
import com.example.demo.entity.Department;
import com.example.demo.entity.Project;

import com.example.demo.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final DepartmentServiceImp departmentServiceImp;

    private final ProjectMapper projectMapper;

    public Project createProject(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setProjectName(projectDTO.getProjectName());
        project.setArea(projectDTO.getArea());

        Optional<Department> department = departmentServiceImp.getDepartmentById(projectDTO.getDepartmentId());
        if(department.isPresent())
            project.setDepartment(department.get());

        return projectRepository.save(project);
    }

    public List<Project> getAllProject() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long projectId) {
        return projectRepository.findById(projectId);
    }

    public Project updateProject(Long projectId, ProjectDTO projectDTO) {
        Optional<Project> project = projectRepository.findById(projectId);
        Project updateProject = new Project();

        updateProject.setProjectName(projectDTO.getProjectName());
        updateProject.setArea(projectDTO.getArea());

        Optional<Department> department = departmentServiceImp.getDepartmentById(projectDTO.getDepartmentId());
        if(department.isPresent())
            updateProject.setDepartment(department.get());

        return projectRepository.save(updateProject);
    }

    public void deleteProjectById(Long projectId) {
        projectRepository.deleteById(projectId);
    }



    //---------------------UNIT TEST WITH JAVA 8 USING STREAM---------------------\\

    public List<ProjectDTO> getAllProjectAndDepartment(){
        List<Project> projects = projectRepository.findAll();
        return projectMapper.toDTOs(projects);
    }
}
