package com.example.demo.service;

import com.example.demo.serviceimp.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> getEmployeeByFirstName(String firstName);

    List<EmployeeDTO> findEmployeeBySalaryLessThan(double salary);

    List<EmployeeDTO> findEmployeeByGender(String gender);
}
