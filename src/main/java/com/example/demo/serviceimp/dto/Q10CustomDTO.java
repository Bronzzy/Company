package com.example.demo.serviceimp.dto;

import com.example.demo.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Q10CustomDTO {
    private Project project;
    private Long totalEmployees;
    private Double totalHours;
}
