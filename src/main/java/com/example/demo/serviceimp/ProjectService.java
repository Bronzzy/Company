package com.example.demo.serviceimp;

import com.example.demo.entity.Assignment;
import com.example.demo.entity.Employee;
import com.example.demo.mapper.ProjectMapper;
import com.example.demo.serviceimp.dto.ProjectDTO;
import com.example.demo.entity.Department;
import com.example.demo.entity.Project;

import com.example.demo.repository.ProjectRepository;

import com.example.demo.serviceimp.dto.ProjectSalaryDTO;
import com.example.demo.serviceimp.dto.ProjectWithEmployeeAndHoursDTO;
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
        if (department.isPresent())
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
        if (department.isPresent())
            updateProject.setDepartment(department.get());

        return projectRepository.save(updateProject);
    }

    public void deleteProjectById(Long projectId) {
        projectRepository.deleteById(projectId);
    }


    //---------------------UNIT TEST WITH JAVA 8 USING STREAM---------------------\\

    public List<ProjectDTO> getAllProjectAndDepartment() {
        List<Project> projects = projectRepository.findAll();
        return projectMapper.toDTOs(projects);
    }

    //10. List all projects with the number of employees and number of hours in a specific area
    public List<ProjectWithEmployeeAndHoursDTO> getProjectWithTotalEmployeesAndHours(String area){
       return projectRepository.findAll().stream()
               .filter(p -> area.equals(p.getArea()))
               .map(p ->{
                   ProjectWithEmployeeAndHoursDTO projectWithEmployeeAndHoursDTO = new ProjectWithEmployeeAndHoursDTO();
                   projectWithEmployeeAndHoursDTO.setProjectId(p.getProjectId());
                   projectWithEmployeeAndHoursDTO.setProjectName(p.getProjectName());
                   projectWithEmployeeAndHoursDTO.setArea(p.getArea());

                   if(p.getAssignments() != null){
                       projectWithEmployeeAndHoursDTO.setTotalEmployee((int) p.getAssignments()
                               .stream()
                               .map(Assignment::getEmployee).distinct().count());
                       projectWithEmployeeAndHoursDTO.setTotalSalary(p.getAssignments()
                               .stream()
                               .mapToDouble(Assignment::getHour).sum());
                   }
                   return projectWithEmployeeAndHoursDTO;
               })
               .collect(Collectors.toList());
    }

    //11. List all projects with total cost (salary) and hours in a specific area
    public List<ProjectSalaryDTO> getProjectSalaryDetailByArea(String area) {
        return projectRepository.findAll().stream()
                .filter(p -> p.getArea().equals(area))
                .map(p -> {
                    ProjectSalaryDTO projectSalaryDTO = new ProjectSalaryDTO();
                    projectSalaryDTO.setProjectId(p.getProjectId());
                    projectSalaryDTO.setProjectName(p.getProjectName());
                    projectSalaryDTO.setArea(p.getArea());

                    if (p.getAssignments() != null) {
                        projectSalaryDTO.setTotalNumberOfHours(p.getAssignments().stream()
                                .mapToDouble(Assignment::getHour).sum());

                        projectSalaryDTO.setTotalSalary(p.getAssignments().stream()
                                .map(Assignment::getEmployee)
                                .mapToDouble(Employee::getSalary)
                                .sum());
                    }
                    return projectSalaryDTO;
                }).collect(Collectors.toList());
    }


}
