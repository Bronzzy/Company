package com.example.demo.rest;

import com.example.demo.serviceimp.dto.DepartmentDTO;
import com.example.demo.entity.Department;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/api/departments")
public interface DepartmentAPI {

    @PostMapping
    ResponseEntity<Department> createDepartment(@RequestBody DepartmentDTO departmentDTO);

    @GetMapping
    ResponseEntity<List<Department>> getAllDepartment();

    @GetMapping(value = "/{deptId}")
    ResponseEntity<Optional<Department>> getDepartmentById(@PathVariable Long deptId);


    @PutMapping("/{deptId}")
    ResponseEntity<Department> updateDepartment(@PathVariable("deptId") Long deptId,
                                                @RequestBody DepartmentDTO departmentDTO);

    @DeleteMapping("/{deptId}")
    ResponseEntity<Void> deleteDepartmentById(@PathVariable("deptId") Long deptId);

    @GetMapping("/startdatelessthan")
    ResponseEntity<List<DepartmentDTO>> findDepartmentStarDateLessThan(@RequestParam("startDate") String startDate);

//    @GetMapping("/byname")
//    ResponseEntity<List<DepartmentDTO>> findDepartmentByName(@RequestParam("name") String name);

}

