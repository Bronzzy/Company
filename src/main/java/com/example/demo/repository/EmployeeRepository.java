package com.example.demo.repository;


import com.example.demo.entity.Employee;
import com.example.demo.serviceimp.dto.CustomEmployeeDTO;
import com.example.demo.serviceimp.dto.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    List<Employee> findByLastNameAndFirstName(String lastName, String firstName);

    List<Employee> findByLastNameLike(String lastName);

    List<Employee> findByLastNameNot(String name);

    List<Employee> findByFirstNameIgnoreCase(String name);

    @Query(value = "SELECT * FROM Employee e where e.first_name like :first_name",nativeQuery = true)
    List<Employee> findEmployeeByFirstName(@Param("first_name") String firstName);

    @Query(value = "SELECT * FROM Employee e where e.salary < :salary",nativeQuery = true)
    List<Employee> findEmployeeBySalaryLessThan(@Param("salary") double salary);

    @Query(value = "SELECT * FROM Employee e where e.gender = :gender",nativeQuery = true)
    List<Employee> findEmployeeByGender(@Param("gender") String gender);

    @Query(name = "Employee.findEmployeeWithMaxSalary",nativeQuery = true)
    CustomEmployeeDTO findEmployeeWithMaxSalary();
}
