package com.example.demo.serviceimp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentStatistic {
    private String departmentName;
    private LocalDate startDate;
    private Integer numberOfEmployees;
    private Integer numberOfMales;
    private Integer numberOfFemales;
    private Integer numberOfU23;

}
