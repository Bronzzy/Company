package com.example.demo.repository;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Gender;
import com.example.demo.entity.Relative;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelativeRepository extends JpaRepository<Relative, Long> {
    List<Relative> findByFullName(String name);

    List<Relative> findByFullNameOrderByGenderDesc(String fullName);

    List<Relative> findByFullNameContaining(String name);

    List<Relative> findByGenderNot(Gender gender);
}
