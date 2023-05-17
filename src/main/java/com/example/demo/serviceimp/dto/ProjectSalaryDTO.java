package com.example.demo.serviceimp.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ProjectSalaryDTO {
    private Long projectId;
    private String projectName;
    private String area;
    private Double totalNumberOfHours;
    private Double totalSalary;
}