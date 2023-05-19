package com.example.demo.serviceimp.dto;

import lombok.*;

@Data
@RequiredArgsConstructor
public class TotalProjectHourAndSalaryDTO {
    private Integer totalProject;
    private Double totalHours;
    private Double totalSalary;
}
