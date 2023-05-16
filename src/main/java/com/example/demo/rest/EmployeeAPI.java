package com.example.demo.rest;

import com.example.demo.entity.Employee;
import com.example.demo.serviceimp.dto.CustomEmployeeDTO;
import com.example.demo.serviceimp.dto.EmployeeDTO;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/api/employees")
public interface EmployeeAPI {
    @PostMapping
    ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDTO employeeDTO);

    @GetMapping
    ResponseEntity<List<Employee>> getAllEmployee();

    @GetMapping("/{employeeId}")
    ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable("employeeId") String employeeId);

    @PutMapping("/{employeeId}")
    ResponseEntity<Employee> updateEmployee(@PathVariable("employeeId") String employeeId,
                                            @RequestBody EmployeeDTO employeeDTO);

    @DeleteMapping("/{employeeId}")
    ResponseEntity<Void> deleteEmployeeById(@PathVariable("employeeId") String employeeId);

    @GetMapping("/getbyfirstandlastname")
    ResponseEntity<List<Employee>> getEmployeeByLastNameAndFirstName(@Param("lastName") String firstName,
                                                                     @Param("firstName") String lastName);

    @GetMapping("/getbylastnamelike")
    ResponseEntity<List<Employee>> getEmployeeByLastNameLike(@Param("lastName") String lastName);

    @GetMapping("/getbylastnamenot")
    ResponseEntity<List<Employee>> getEmployeeByLastNameNot(@Param("lastName") String lastName);

    @GetMapping("/getbyfirstnameignorecase")
    ResponseEntity<List<Employee>> getEmployeeByFirstNameIgnoreCase(@Param("firstName") String firstName);

    //RETURN DTO, THROW EXCEPTION, TRY & CATCH
    @GetMapping("/getemployeebyfirstname")
    ResponseEntity<List<EmployeeDTO>> getEmployeeByFirstName(@Param("firstName") @NotBlank  String firstName);

    @GetMapping("/getbysalarylessthan")
    ResponseEntity<List<EmployeeDTO>> findEmployeeBySalaryLessThan(@RequestParam("salary") double salary);

    @GetMapping("/getbygender")
    ResponseEntity<List<EmployeeDTO>> findEmployeeByGender(@RequestParam("gender") String gender);

    @GetMapping("/findemployeewithmaxsalary")
    ResponseEntity<CustomEmployeeDTO> findEmployeeWithMaxSalary();
}

