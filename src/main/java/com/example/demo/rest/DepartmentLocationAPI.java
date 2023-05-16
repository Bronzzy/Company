package com.example.demo.rest;

import com.example.demo.serviceimp.dto.DepartmentLocationDTO;
import com.example.demo.entity.DepartmentLocation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/api/department-locations")
public interface DepartmentLocationAPI {

    @PostMapping
    ResponseEntity<DepartmentLocation> createDepartmentLocation(@RequestBody DepartmentLocationDTO departmentLocationDTO);

    @GetMapping
    ResponseEntity<List<DepartmentLocation>> getAllDepartmentLocation();


    @GetMapping("/{deptLocationId}")
    ResponseEntity<Optional<DepartmentLocation>> getDepartmentLocationById
                                                  (@PathVariable("deptLocationId") Long deptLocationId);


    @PutMapping("/{deptLocationID}")
    ResponseEntity<DepartmentLocation> updateDepartmentLocation(@RequestBody  DepartmentLocationDTO departmentLocationDTO,
                                                                @PathVariable("deptLocationID") Long deptLocID);

    @DeleteMapping("/{deptLocationID}")
    ResponseEntity<Void> deleteDepartmentLocationById(@PathVariable("deptLocationID") Long deptLocationID);
}
