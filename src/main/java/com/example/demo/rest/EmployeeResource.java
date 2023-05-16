package com.example.demo.rest;

import com.example.demo.entity.Employee;
import com.example.demo.exception.ResponseException;
import com.example.demo.serviceimp.EmployeeServiceImp;
import com.example.demo.serviceimp.dto.CustomEmployeeDTO;
import com.example.demo.serviceimp.dto.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EmployeeResource implements EmployeeAPI {
    private final EmployeeServiceImp employeeServiceImp;

    @Override
    public ResponseEntity<Employee> createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeServiceImp.createEmployee(employeeDTO);
        return ResponseEntity.created(URI.create("/api/employees/" + employee.getEmployeeId())).body(employee);
    }

    @Override
    public ResponseEntity<List<Employee>> getAllEmployee() {
        return ResponseEntity.ok(employeeServiceImp.getAllEmployees());
    }

    @Override
    public ResponseEntity<Optional<Employee>> getEmployeeById(String employeeId) {
        return ResponseEntity.ok(employeeServiceImp.getEmployeeById(employeeId));
    }

    @Override
    public ResponseEntity<Employee> updateEmployee(String employeeId, EmployeeDTO employeeDTO) {
        Employee employee = employeeServiceImp.updateEmployee(employeeId, employeeDTO);
        return ResponseEntity.created(URI.create("/api/employees/" + employee.getEmployeeId())).body(employee);
    }

    @Override
    public ResponseEntity<Void> deleteEmployeeById(String employeeId) {
        employeeServiceImp.deleteEmployeeById(employeeId);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<List<Employee>> getEmployeeByLastNameAndFirstName(String lastName, String firstName) {
        return ResponseEntity.ok(employeeServiceImp.getEmployeeByLastNameAndFirstName(lastName, firstName));
    }

    public ResponseEntity<List<Employee>> getEmployeeByLastNameLike(String firsName) {
        return ResponseEntity.ok(employeeServiceImp.getEmployeeByLastNameLike(firsName));
    }

    public ResponseEntity<List<Employee>> getEmployeeByLastNameNot(String lastName) {
        return ResponseEntity.ok(employeeServiceImp.getEmployeeByLastNameNot(lastName));
    }

    public ResponseEntity<List<Employee>> getEmployeeByFirstNameIgnoreCase(String firstName) {
        return ResponseEntity.ok(employeeServiceImp.getEmployeeByFirstNameIgnoreCase(firstName));
    }

    // RETURN DTO, THROW EXCEPTION, TRY & CATCH
    public ResponseEntity<List<EmployeeDTO>> getEmployeeByFirstName(String firstName) {
        try{
            return ResponseEntity.ok(employeeServiceImp.getEmployeeByFirstName(firstName));
        } catch(ResponseException e){
            throw e;
        }
    }

    public ResponseEntity<List<EmployeeDTO>> findEmployeeBySalaryLessThan(double salary){
        try{
            return ResponseEntity.ok(employeeServiceImp.findEmployeeBySalaryLessThan(salary));
        }catch(ResponseException e){
            throw e;
        }
    }

    public ResponseEntity<List<EmployeeDTO>> findEmployeeByGender(String gender){
        try{
            return ResponseEntity.ok(employeeServiceImp.findEmployeeByGender(gender));
        }catch(ResponseException e){
            throw e;
        }
    }

    public ResponseEntity<CustomEmployeeDTO> findEmployeeWithMaxSalary(){
        try{
            return ResponseEntity.ok(employeeServiceImp.findEmployeeWithMaxSalary());
        }catch (ResponseException e){
            throw e;
        }
    }
}
