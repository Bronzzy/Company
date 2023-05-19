package com.example.demo.serviceimp;

import com.example.demo.entity.Assignment;
import com.example.demo.entity.Employee;
import com.example.demo.mapper.ProjectMapper;
import com.example.demo.repository.AssignmentRepository;
import com.example.demo.serviceimp.dto.*;
import com.example.demo.entity.Department;
import com.example.demo.entity.Project;

import com.example.demo.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.expression.spel.ast.Assign;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final DepartmentServiceImp departmentServiceImp;

    private final AssignmentRepository assignmentRepository;

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

    //6. List of Projects at VIETNAM, + number of employees and total num_of_hour
    public ProjectWithEmployeeAndHoursInVNDTO getProjectWithTotalEmployeesAndHoursInVietNam() {
//        return projectRepository.findAll().stream()
//                .filter(p -> "VIETNAM".equals(p.getArea()))
//                .map(p -> {
//                    ProjectWithEmployeeAndHoursDTO projectWithEmployeeAndHoursDTO = new ProjectWithEmployeeAndHoursDTO();
//                    projectWithEmployeeAndHoursDTO.setProjectId(p.getProjectId());
//                    projectWithEmployeeAndHoursDTO.setProjectName(p.getProjectName());
//                    projectWithEmployeeAndHoursDTO.setArea(p.getArea());
//
//                    if (p.getAssignments() != null) {
//                        //get total employee
//                        projectWithEmployeeAndHoursDTO.setTotalEmployee((int) p.getAssignments()
//                                .stream()
//                                .map(Assignment::getEmployee).distinct().count());
//                        //get total hour
//                        projectWithEmployeeAndHoursDTO.setTotalSalary(p.getAssignments()
//                                .stream()
//                                .mapToDouble(Assignment::getHour).sum());
//                    }
//                    return projectWithEmployeeAndHoursDTO;
//                })
//                .collect(Collectors.toList());
        ProjectWithEmployeeAndHoursInVNDTO raw = new ProjectWithEmployeeAndHoursInVNDTO();

        List<Project> projects = projectRepository.findAll().stream()
                .filter(p -> "VIETNAM".equals(p.getArea()))
                .collect(Collectors.toList());

        String projectName = projects.stream()
                .map(Project::getProjectName)
                .collect(Collectors.joining(", "));

        int totalEmployee = (int) projects.stream()
                .map(project -> project.getAssignments().stream()
                        .map(Assignment::getEmployee).distinct().count()).count();

        double totalHours = projects.stream()
                .map(Project::getAssignments)
                .mapToDouble(assignments -> assignments.stream()
                        .mapToDouble(Assignment::getHour).sum()).sum();

        raw.setTotalHour(totalHours);
        raw.setTotalEmployee(totalEmployee);
        raw.setProjectName(projectName);

        return raw;
    }

    //7. List of projects at VIETNAM total, num_of_hours, and Total salary
    public TotalProjectHourAndSalaryDTO getProjectSalaryDetailByArea() {
        TotalProjectHourAndSalaryDTO totalProjectHourAndSalaryDTOS = new TotalProjectHourAndSalaryDTO();
        List<Project> projects = projectRepository.findAll().stream()
                .filter(p -> "Wakayama-shi".equals(p.getArea()))
                .collect(Collectors.toList());
        int totalProject = projects.size();
        double totalHour = projects.stream()
                .map(p -> p.getAssignments())
                .mapToDouble(a -> a.stream()
                        .mapToDouble(ass -> ass.getHour()).sum()).sum();

        double totalSalary = projects.stream()
                .map(p -> p.getAssignments())
                .mapToDouble(a -> a.stream()
                        .mapToDouble(ass -> ass.getEmployee().getSalary()).sum()).sum();

        totalProjectHourAndSalaryDTOS.setTotalProject(totalProject);
        totalProjectHourAndSalaryDTOS.setTotalSalary(totalSalary);
        totalProjectHourAndSalaryDTOS.setTotalHours(totalHour);

        return totalProjectHourAndSalaryDTOS;
    }

    //10. List all projects with the number of employees and number of hours in a specific area
    public List<ProjectWithEmployeeAndHoursDTO> getProjectWithTotalEmployeesAndHours(String area) {
        return projectRepository.findAll().stream()
                .filter(p -> area.equals(p.getArea()))
                .map(p -> {
                    ProjectWithEmployeeAndHoursDTO projectWithEmployeeAndHoursDTO = new ProjectWithEmployeeAndHoursDTO();
                    projectWithEmployeeAndHoursDTO.setProjectId(p.getProjectId());
                    projectWithEmployeeAndHoursDTO.setProjectName(p.getProjectName());
                    projectWithEmployeeAndHoursDTO.setArea(p.getArea());

                    if (p.getAssignments() != null) {
                        //get total employee
                        projectWithEmployeeAndHoursDTO.setTotalEmployee((int) p.getAssignments()
                                .stream()
                                .map(Assignment::getEmployee).distinct().count());
                        //get total hour
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


    //-------custom--------\\
    public List<Q10CustomDTO> getCustomQuery() {
        List<Q10CustomDTO> q10CustomDTOS = new ArrayList<>();
        List<Assignment> assignments = assignmentRepository.findAll();


        return q10CustomDTOS;
    }
}

