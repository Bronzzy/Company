package com.example.demo.rest;

import com.example.demo.exception.CompanyException;
import com.example.demo.exception.ResponseException;
import com.example.demo.serviceimp.dto.DepartmentDTO;
import com.example.demo.entity.Department;
import com.example.demo.serviceimp.DepartmentServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequiredArgsConstructor
public class DepartmentResource implements DepartmentAPI {

    private final DepartmentServiceImp departmentServiceImp;
//    @Override
//    public ResponseEntity<Department> createDepartment(DepartmentDTO departmentDTO) {
//        Department department = departmentServiceImp.createDepartment(departmentDTO);
//        return ResponseEntity.created(URI.create("/api/departments/" + department.getId())).body(department);
//    }

    @Override
    public ResponseEntity<Department> createDepartment(DepartmentDTO departmentDTO) {
        return null;
    }

    @Override
    public ResponseEntity<List<Department>> getAllDepartment(){
        return ResponseEntity.ok(departmentServiceImp.getAllDepartment());
    }

    public ResponseEntity<Optional<Department>> getDepartmentById(Long deptId){
        return ResponseEntity.ok(departmentServiceImp.getDepartmentById(deptId));
    }

    @Override
    public ResponseEntity<Department> updateDepartment(Long deptId, DepartmentDTO departmentDTO) {
        Department department = departmentServiceImp.updateDepartment(deptId, departmentDTO);
        return ResponseEntity.created(URI.create("/api/departments/" + department.getId())).body(department);
//    public ResponseEntity<Department> updateDepartment(Department department) {
//        Department departmentTemp = departmentService.updateDepartment(department);
//        return ResponseEntity.created(URI.create("/api/departments/" + department.getId())).body(department);
    }

    public ResponseEntity<Void> deleteDepartmentById(Long deptId){
        departmentServiceImp.deleteDepartmentById(deptId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<DepartmentDTO>> findDepartmentStarDateLessThan(String startDate) {
        if(!validateDateFormat(startDate)){
            throw CompanyException.badRequest("InvalidDateFormat", "Date format must be yyyy-mm-dd");
        }
        //PARSE STRING TO DATE FORMAT
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(startDate, formatter);
        return ResponseEntity.ok(departmentServiceImp.findDepartmentStartDateLessThan(localDate));
    }


//    @Override
//    public ResponseEntity<List<Department>> getDepartmentStartingWith(String name){
//        return ResponseEntity.ok(departmentService.getByDepartmentStartingWith(name));
//    }

//    public ResponseEntity<List<DepartmentDTO>> findDepartmentByName(String name){
//        try{
//            return ResponseEntity.ok(departmentServiceImp.findDepartmentByName(name));
//        }catch (ResponseException e){
//            throw e;
//        }
//    }





    //METHOD FOR CHECKING VALIDATE STRING IN THE FORMAT "YYYY-MM-DD"
    private static boolean validateDateFormat(String dateString){
        String pattern = "^\\d{4}-\\d{2}-\\d{2}$"; // REGEX PATTERN FOR YYYY-MM-DD FORMAT
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(dateString);

        return matcher.matches();
    }
}
