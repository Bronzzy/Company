package com.example.demo.serviceimp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomAssignmentEmployeeProject {
    private String projectName;
    private Long numberOfEmployees;
    private Double totalHours;

}
