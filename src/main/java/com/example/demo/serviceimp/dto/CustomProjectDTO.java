package com.example.demo.serviceimp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomProjectDTO {

    private String area;
    private String projectName;
    private Integer numberOfEmployee;
    private Double totalHours;

}
