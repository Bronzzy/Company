package com.example.demo.repository;

import com.example.demo.entity.Department;
import com.example.demo.serviceimp.dto.DepartmentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query(name = "findDepartmentStartDateLessThan", nativeQuery = true)
    List<DepartmentDTO> findDepartmentStartDateLessThan(@Param("startDate") LocalDate startDate);

    @Query(name = "SELECT * from Department d where d.department_name = :name", nativeQuery = true)
    List<Department> findDepartmentByName(String name);
}
