package com.example.demo.mapper;

import com.example.demo.entity.Employee;
import com.example.demo.serviceimp.dto.EmployeeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

//    @Mapping(target = "departmentName", source = "department.name")
    EmployeeDTO toDTo(Employee employee);

    List<EmployeeDTO> toDtos(List<Employee> employees);
}
