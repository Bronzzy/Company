package com.example.demo.serviceimp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectWithEmployeeAndHoursDTO {
    private String projectName;
    private Integer totalEmployee;
    private Double totalSalary;
    private Long ProjectId;
    private String area;
}
