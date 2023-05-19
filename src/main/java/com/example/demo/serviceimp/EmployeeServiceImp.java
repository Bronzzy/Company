package com.example.demo.serviceimp;


import com.example.demo.entity.Project;
import com.example.demo.entity.Relative;
import com.example.demo.exception.CompanyException;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.repository.RelativeRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.serviceimp.dto.CustomEmployeeDTO;

import com.example.demo.serviceimp.dto.EmployeeDTO;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImp implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final DepartmentServiceImp departmentServiceImp;

    private final EmployeeMapper employeeMapper;

    private final ProjectService projectService;

    private final RelativeRepository relativeRepository;

    public Employee createEmployee(EmployeeDTO employeeDTO) {

        Employee employee = new Employee();

        employee.setEmployeeId(employeeDTO.getEmployeeId());
        employee.setDob(employeeDTO.getDob());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setMiddleName(employeeDTO.getMiddleName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setGender(employeeDTO.getGender());
        employee.setSalary(employeeDTO.getSalary());

        Optional<Department> department = departmentServiceImp.getDepartmentById((employeeDTO.getDepartmentID()));
        if (department.isPresent())
            employee.setDepartment((department.get()));

        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(String employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public Employee updateEmployee(String employeeId, EmployeeDTO employeeDTO) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        Employee updateEmployee = new Employee();

        updateEmployee.setDob(employeeDTO.getDob());
        updateEmployee.setFirstName((employeeDTO.getFirstName()));
        updateEmployee.setMiddleName((employeeDTO.getMiddleName()));
        updateEmployee.setLastName((employeeDTO.getLastName()));
        updateEmployee.setGender(employeeDTO.getGender());
        updateEmployee.setSalary((employeeDTO.getSalary()));

        Optional<Department> department = departmentServiceImp.getDepartmentById((employeeDTO.getDepartmentID()));
        if (department.isPresent())
            updateEmployee.setDepartment((department.get()));

        return employeeRepository.save(updateEmployee);
    }

    public void deleteEmployeeById(String employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public List<Employee> getEmployeeByLastNameAndFirstName(String lastName, String firstName) {
        return employeeRepository.findByLastNameAndFirstName(lastName, firstName);
    }


    public List<Employee> getEmployeeByLastNameLike(String lastName) {
        return employeeRepository.findByLastNameLike(lastName);
    }

    public List<Employee> getEmployeeByLastNameNot(String lastName) {
        return employeeRepository.findByLastNameNot(lastName);
    }

    public List<Employee> getEmployeeByFirstNameIgnoreCase(String firstName) {
        return employeeRepository.findByFirstNameIgnoreCase(firstName);
    }

    //CUSTOM QUERY, THROW EXCEPTION, TRY & CATCH
    @Override
    public List<EmployeeDTO> getEmployeeByFirstName(String firstName) {
        log.info("find employee by first name {}", firstName);
        List<Employee> employees = employeeRepository.findEmployeeByFirstName(firstName);
        if (firstName == null || firstName.isBlank()) {
            throw CompanyException.badRequest("FirstNameEmpty", "First name is null or empty");
        }
        if (employees.isEmpty()) {
            throw CompanyException.EmployeeNotFound();
        }
        return employeeMapper.toDtos(employees);
    }

    public List<EmployeeDTO> findEmployeeBySalaryLessThan(double salary) {
        log.info("find employee by salary less than {}", salary);
        List<Employee> employees = employeeRepository.findEmployeeBySalaryLessThan(salary);
        if (salary <= 0) {
            throw CompanyException.badRequest("SalaryInvalid", "Salary can not be less than or equal 0");
        }
        return employeeMapper.toDtos(employees);
    }

    public List<EmployeeDTO> findEmployeeByGender(String gender) {
        log.info("find employee by gender {}", gender);
        List<Employee> employees = employeeRepository.findEmployeeByGender(gender);
        if (!gender.equals("Male") && !gender.equals("Female") && !gender.equals("Other"))
            throw CompanyException.badRequest("GenderInvalid", "Gender is not valid");
        return employeeMapper.toDtos(employees);
    }

    public CustomEmployeeDTO findEmployeeWithMaxSalary(){
        log.info("find employee with max salary{}");
      //  EmployeeDTO employee =
          return employeeRepository.findEmployeeWithMaxSalary();
//        return employeeMapper.toDTo(employee);
    }




    //---------------------UNIT TEST WITH JAVA 8 USING STREAM---------------------\\

    //2. Get all employees that have birth months the same with input month

    public List<EmployeeDTO> getEmployeeWithSameBirthMonths(int month){
        List<Employee> employees = employeeRepository.findAll();
        employees = employees.stream()
                .filter(e -> e.getDob().getMonthValue() == month)
                .collect(Collectors.toList());
        return employeeMapper.toDtos(employees);
    }

    public List<EmployeeDTO> getEmployeeWithSortedRelatives() {
        List<Employee> employees = employeeRepository.findAll();
        employees = employees.stream()
                .filter(e -> !e.getRelatives().isEmpty())
                .collect(Collectors.toList());

        for (Employee e : employees) {
            Relative relative = relativeRepository.findAll().stream()
                    .filter(r -> r.getEmployee().equals(e))
                    .sorted(Comparator.comparingInt(relatives -> relatives.getRelationship().equals("father") ? 0 :
                            relatives.getRelationship().equals("mother") ? 1 : 2))
                    .findFirst().get();

            List<Relative> relatives = new ArrayList<>();
            relatives.add(relative);

            e.setRelatives(relatives);
        }
        return employeeMapper.toDtos(employees);
    }

    //5.List of departments and projects that they manage
    void getAllDepartmentAndProject() {
        List<Project> project = projectService.getAllProject();
        project.forEach(System.out::println);
    }

    //12. List of employees who have not been assigned to any project
     public List<EmployeeDTO> getEmployeesNotAssigned() {
        List<Employee> employees= employeeRepository.findAll().stream()
                .filter(e -> e.getAssignment() == null)
                .collect(Collectors.toList());
        return employeeMapper.toDtos(employees);
    }
}

