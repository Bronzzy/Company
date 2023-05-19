package com.example.demo.serviceimp;

import com.example.demo.entity.Employee;
import com.example.demo.exception.CompanyException;
import com.example.demo.mapper.DepartmentMapper;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.serviceimp.dto.CustomDepartmentDTO;
import com.example.demo.serviceimp.dto.DepartmentDTO;
import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.serviceimp.dto.DepartmentStatistic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor

public class DepartmentServiceImp {

    private final DepartmentRepository departmentRepository;
    private DepartmentMapper departmentMapper;

    private final EmployeeRepository employeeRepository;

    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(Long deptId) {
        return departmentRepository.findById(deptId);
    }

    public Department updateDepartment(Long deptId, DepartmentDTO departmentDTO) {

        Optional<Department> department = departmentRepository.findById(deptId);

        Department updateDepartment = new Department();

        updateDepartment.setName(departmentDTO.getName());
        updateDepartment.setStartDate(departmentDTO.getStartDate());
        return departmentRepository.save(updateDepartment);
    }

    public void deleteDepartmentById(Long deptId) {
        departmentRepository.deleteById(deptId);
    }


    ///CUSTOM QUERY, THROW EXCEPTION, TRY & CATCH
    public DepartmentDTO createDepartment(CustomDepartmentDTO customDepartmentDTO) {
        log.info("create new department {}", customDepartmentDTO);
        if (customDepartmentDTO.getName() == null || customDepartmentDTO.getName().isBlank()) {
            throw CompanyException.badRequest("DepartmentNameEmpty", "Department name can't be empty or null");
        }

        Department department = Department.builder()
                .name(customDepartmentDTO.getName())
                .startDate(customDepartmentDTO.getStartDate())
                .build();
        return departmentMapper.toDto(departmentRepository.save(department));
    }

    public List<DepartmentDTO> findDepartmentStartDateLessThan(LocalDate startDate) {
        log.info("find department with start date less than {}", startDate);
        return departmentRepository.findDepartmentStartDateLessThan(startDate);
    }

    //---------------------QUERY WITH JAVA 8 USING STREAM---------------------\\

    //1. Provide department statistics report: Department Name, Start Date, number of employees, number of females, number of
    //males, number of U23.
    public List<DepartmentStatistic> getDepartmentStatistics() {
        List<DepartmentStatistic> departmentStatistics = new ArrayList<>();
        List<Department> departments = departmentRepository.findAll();
        for (Department d : departments) {

            DepartmentStatistic departmentStatistic = new DepartmentStatistic();
            departmentStatistic.setDepartmentName(d.getName());
            departmentStatistic.setStartDate(d.getStartDate());

            List<Employee> employees = employeeRepository.findByDepartment(d);
            Integer numberOfEmployees = ((int) employees.stream().count());
            Integer numberOfMales = ((int) employees.stream().filter(e -> "Female".equals(e.getGender())).count());
            Integer numberOfFemales = ((int) employees.stream().filter(e -> "Male".equals(e.getGender())).count());
            Integer numberOfU23 = ((int) employees.stream().filter(e -> LocalDate.now().getYear() - e.getDob().getYear() < 23).count());


            departmentStatistic.setNumberOfEmployees(numberOfEmployees);
            departmentStatistic.setNumberOfFemales(numberOfFemales);
            departmentStatistic.setNumberOfMales(numberOfMales);
            departmentStatistic.setNumberOfU23(numberOfU23);

            departmentStatistics.add(departmentStatistic);
        }
        return departmentStatistics;
    }

}
