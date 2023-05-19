package com.example.demo.serviceimp;

import com.example.demo.serviceimp.dto.AssignmentDTO;
import com.example.demo.entity.Assignment;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Project;
import com.example.demo.repository.AssignmentRepository;
import com.example.demo.serviceimp.dto.CustomAssignmentEmployeeProject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImp {
    private final AssignmentRepository assignmentRepository;
    private final EmployeeServiceImp employeeServiceImp;
    private final ProjectService projectService;

    public Assignment createAssignment(AssignmentDTO assignmentDTO) {
        Assignment assignment = new Assignment();
        assignment.setHour(assignmentDTO.getHour());

        Optional<Project> project = projectService.getProjectById(assignmentDTO.getProjectId());
        if (project.isPresent())
            assignment.setProject(project.get());
        Optional<Employee> employee = employeeServiceImp.getEmployeeById(assignmentDTO.getEmployeeId());
        if (employee.isPresent())
            assignment.setEmployee(employee.get());

        return assignmentRepository.save(assignment);
    }

    public List<Assignment> getAllAssignment() {
        return assignmentRepository.findAll();
    }

    public Optional<Assignment> getAssignmentById(Long assignmentId) {
        return assignmentRepository.findById(assignmentId);
    }

    public Assignment updateAssignment(Long assignmentId, AssignmentDTO assignmentDTO) {
        Optional<Assignment> assignment = assignmentRepository.findById(assignmentId);
        Assignment updateAssignment = new Assignment();
        updateAssignment.setHour(assignmentDTO.getHour());

        Optional<Project> project = projectService.getProjectById(assignmentDTO.getProjectId());
        if (project.isPresent())
            updateAssignment.setProject(project.get());

        Optional<Employee> employee = employeeServiceImp.getEmployeeById(assignmentDTO.getEmployeeId());
        if (employee.isPresent())
            updateAssignment.setEmployee(employee.get());

        return assignmentRepository.save(updateAssignment);
    }

    public void deleteAssignment(Long assignmentId) {
        assignmentRepository.deleteById(assignmentId);
    }

    //6. List of Projects at VIETNAM, + number of employees and total num_of_hour
    public CustomAssignmentEmployeeProject findProjectInArea(String area) {
        List<Assignment> assignments = assignmentRepository.findAll();
        assignments = assignments.stream().filter(a -> area.equalsIgnoreCase(a.getProject().getArea()))
                .collect(Collectors.toList());

        String
                names = assignments.stream()
                .map(a -> a.getProject().getProjectName())
                .distinct()
                .collect(Collectors.joining());

        Double sumHour = assignments.stream().mapToDouble(Assignment::getHour).sum();
        Long countEmployee = assignments.stream().count();

        CustomAssignmentEmployeeProject customAssignmentEmployeeProject = CustomAssignmentEmployeeProject.builder()
                .projectName(names)
                .totalHours(sumHour)
                .numberOfEmployees(countEmployee)
                .build();
        return customAssignmentEmployeeProject;
        //DI TU PROJECT THAY VI ASSIGNMENT
    }
    //Test
    public List<CustomAssignmentEmployeeProject> query6(){
        //return List Project at Area == "Riyom"
        List<Project> projectList = projectService.getAllProject().stream()
                .filter(p -> p.getArea().equals("Riyom"))
                .collect(Collectors.toList());

        //List of Custom DTO
        List<CustomAssignmentEmployeeProject> customAssignmentEmployeeProjectList = new ArrayList<>();

        //using for loop to compare project Id, using count() and reduce()
        for(int i = 0; i < projectList.size(); i++){
            int finI = i;
            Long numberOfEmps =(Long) assignmentRepository.findAll().stream()
                    .filter(a -> a.getProject().getProjectId() == projectList.get(finI).getProjectId())
                    .count();
            Double numberOfHour =  assignmentRepository.findAll().stream()
                    .filter(a -> a.getProject().getProjectId() == projectList.get(finI).getProjectId())
                    .map(Assignment::getHour)
                    .reduce(0d, (total, element) -> total + element);
            CustomAssignmentEmployeeProject customAssignmentEmployeeProject = new CustomAssignmentEmployeeProject(projectList.get(finI).getProjectName(), numberOfEmps ,numberOfHour);
            customAssignmentEmployeeProjectList.add(customAssignmentEmployeeProject);
        }
        return customAssignmentEmployeeProjectList;
    }

    //7. List of projects at VIETNAM total, num_of_hours, and Total salary

}
