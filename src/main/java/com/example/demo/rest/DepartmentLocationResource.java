package com.example.demo.rest;

import com.example.demo.serviceimp.dto.DepartmentLocationDTO;
import com.example.demo.entity.DepartmentLocation;
import com.example.demo.serviceimp.DepartmentLocationServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class DepartmentLocationResource implements DepartmentLocationAPI {
    private final DepartmentLocationServiceImp departmentLocationServiceImp;

    @Override
    public ResponseEntity<DepartmentLocation> createDepartmentLocation(DepartmentLocationDTO departmentLocationDTO) {
        DepartmentLocation departmentLocation = departmentLocationServiceImp.createDepartmentLocation(departmentLocationDTO);
        return ResponseEntity.created(URI.create("/api/department-locations/" + departmentLocation.getId())).body(departmentLocation);
    }

    @Override
    public ResponseEntity<List<DepartmentLocation>> getAllDepartmentLocation() {
        return ResponseEntity.ok(departmentLocationServiceImp.getAllDepartmentLocation());
    }


    public ResponseEntity<Optional<DepartmentLocation>> getDepartmentLocationById(Long deptLocationId) {
        return ResponseEntity.ok(departmentLocationServiceImp.getDepartmentLocationById(deptLocationId));
    }

    @Override
    public ResponseEntity<DepartmentLocation> updateDepartmentLocation(DepartmentLocationDTO departmentLocationDTO, Long deptLocId) {
        DepartmentLocation departmentLocation = departmentLocationServiceImp.updateDepartmentLocation(departmentLocationDTO, deptLocId);
        return ResponseEntity.created(URI.create("/api/department-locations/" + departmentLocation.getId())).body(departmentLocation);
    }

    public ResponseEntity<Void> deleteDepartmentLocationById(Long deptLocationId) {
        departmentLocationServiceImp.deleteDepartmentById(deptLocationId);
        return ResponseEntity.noContent().build();
    }
}
