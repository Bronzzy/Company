package com.example.demo.serviceimp;

import com.example.demo.serviceimp.dto.AssignmentDTO;
import com.example.demo.entity.Assignment;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Project;
import com.example.demo.repository.AssignmentRepository;
import com.example.demo.serviceimp.dto.CustomAssignmentEmployeeProject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public CustomAssignmentEmployeeProject findProjectInArea(String area) {
        List<Assignment> assignments = assignmentRepository.findAll();
        assignments = assignments.stream().filter(a -> area.equalsIgnoreCase(a.getProject().getArea()))
                .collect(Collectors.toList());
        String names = assignments.stream()
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
    }
}
