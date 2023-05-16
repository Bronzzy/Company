package com.example.demo.serviceimp;

import com.example.demo.exception.CompanyException;
import com.example.demo.mapper.DepartmentMapper;
import com.example.demo.serviceimp.dto.CustomDepartmentDTO;
import com.example.demo.serviceimp.dto.DepartmentDTO;
import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor

public class DepartmentServiceImp {

    private DepartmentRepository departmentRepository;

    private DepartmentMapper departmentMapper;

    public List<Department> getAllDepartment(){
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(Long deptId){
        return departmentRepository.findById(deptId);
    }

    public Department updateDepartment(Long deptId, DepartmentDTO departmentDTO){

        Optional<Department> department = departmentRepository.findById(deptId);

        Department updateDepartment = new Department();

        updateDepartment.setName(departmentDTO.getName());
        updateDepartment.setStartDate(departmentDTO.getStartDate());
        return departmentRepository.save(updateDepartment);

//    public Department updateDepartment(Department department){
////        Department department = getDepartmentById(id).get();
//
//        Department departmentTemp = getDepartmentById(department.getId()).get();
////        department.setName(departmentDTO.getName());
////        department.setStartDate(departmentDTO.getStartDate());
//        return departmentRepository.save(department);
//    }
    }
    public void deleteDepartmentById(Long deptId){
        departmentRepository.deleteById(deptId);
    }



    ///CUSTOM QUERY, THROW EXCEPTION, TRY & CATCH
    public DepartmentDTO createDepartment(CustomDepartmentDTO customDepartmentDTO){
        log.info("create new department {}", customDepartmentDTO);
        if(customDepartmentDTO.getName() == null || customDepartmentDTO.getName().isBlank()){
            throw CompanyException.badRequest("DepartmentNameEmpty","Department name can't be empty or null");
        }

        Department department = Department.builder()
                .name(customDepartmentDTO.getName())
                .startDate(customDepartmentDTO.getStartDate())
                .build();
        return departmentMapper.toDto(departmentRepository.save(department));
    }

    public List<DepartmentDTO> findDepartmentStartDateLessThan (LocalDate startDate){
        log.info("find department with start date less than {}",startDate);
        return departmentRepository.findDepartmentStartDateLessThan(startDate);
    }

//    public List<DepartmentDTO> findDepartmentByName(String name){
//        log.info("find department by name {}",name);
//        List<Department> department = departmentRepository.findDepartmentByName(name);
//        if(name == null || name.isBlank()) {
//            throw CompanyException.badRequest("DepartmentNameEmpty", "Department name can't be null or empty");
//        }
//        if(department.isEmpty()){
//            throw CompanyException.DepartmentNotFound();
//        }
//        return DepartmentMapper.INSTANCE.toDtos(department);
//    }


}
