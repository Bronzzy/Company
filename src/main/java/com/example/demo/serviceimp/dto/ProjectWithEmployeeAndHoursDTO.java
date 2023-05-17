package com.example.demo.serviceimp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectWithEmployeeAndHoursDTO {
    private Long projectId;
    private String projectName;
    private String area;
    private Integer totalEmployee;
    private Double totalSalary;
}
