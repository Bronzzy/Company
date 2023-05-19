package com.example.demo.serviceimp;

import com.example.demo.entity.*;
import com.example.demo.serviceimp.dto.DepartmentStatistic;
import com.example.demo.serviceimp.dto.EmployeeDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.constraints.AssertTrue;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class EmployeeServiceImpTest {
    @Autowired
    private EmployeeServiceImp employeeServiceImp;
    @Autowired
    private RelativeService relativeService;
    @Autowired
    private DepartmentServiceImp departmentServiceImpl;
    @Autowired
    private ProjectService projectService;

    @Test
    void getAllEmployees() {
        List<Employee> employees = employeeServiceImp.getAllEmployees();
        //employees.forEach(e -> System.out.println(e));
        employees.forEach(System.out::println);
    }

    @Test
    void getEmployeeWithSameBirthMonths() {
        int month = 3;
        List<EmployeeDTO> tempList = employeeServiceImp.getEmployeeWithSameBirthMonths(month);
        assertEquals(2,tempList.size());
        tempList.forEach(System.out::println);
    }

    @Test
    void getEmployeeWithSortedRelatives() {
        List<EmployeeDTO> temp = employeeServiceImp.getEmployeeWithSortedRelatives();
        temp.forEach(System.out::println);
    }
}