package com.example.demo.serviceimp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private String employeeId;
    private LocalDate dob;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private Double salary;
    private Long departmentID;
}
