package com.example.demo.serviceimp;

import com.example.demo.exception.CompanyException;
import com.example.demo.serviceimp.dto.DepartmentLocationDTO;
import com.example.demo.entity.Department;
import com.example.demo.entity.DepartmentLocation;
import com.example.demo.repository.DepartmentLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentLocationServiceImp {

    private final DepartmentLocationRepository departmentLocationRepository;

    private final DepartmentServiceImp departmentServiceImp;

    public DepartmentLocation createDepartmentLocation(DepartmentLocationDTO departmentLocationDTO) {
        DepartmentLocation departmentLocation = new DepartmentLocation();
        departmentLocation.setLocation(departmentLocationDTO.getLocation());

        Department department = departmentServiceImp.getDepartmentById(departmentLocationDTO.getDepartmentId()).orElseThrow(CompanyException::DepartmentNotFound);

            departmentLocation.setDepartment(department);

        return departmentLocationRepository.save(departmentLocation);
    }

    public List<DepartmentLocation> getAllDepartmentLocation() {
        return departmentLocationRepository.findAll();
    }

    public Optional<DepartmentLocation> getDepartmentLocationById(Long deptLocationId) {
        return departmentLocationRepository.findById(deptLocationId);
    }

    public DepartmentLocation updateDepartmentLocation(DepartmentLocationDTO departmentLocationDTO, Long id) {
        Optional<DepartmentLocation> departmentLocation = departmentLocationRepository.findById(id);
        DepartmentLocation updateDepartmentLocation = new DepartmentLocation();

        updateDepartmentLocation.setLocation((departmentLocationDTO.getLocation()));

        Optional<Department> department = departmentServiceImp.getDepartmentById(departmentLocationDTO.getDepartmentId());
        if (department.isPresent())
            updateDepartmentLocation.setDepartment(department.get());

        return departmentLocationRepository.save(updateDepartmentLocation);
    }

    public void deleteDepartmentById(Long deptLocationId) {
        departmentLocationRepository.deleteById(deptLocationId);
    }
}
