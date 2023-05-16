package com.example.demo.serviceimp;

import com.example.demo.entity.Gender;
import com.example.demo.serviceimp.dto.RelativeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Relative;
import com.example.demo.repository.RelativeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RelativeService {
    private final RelativeRepository relativeRepository;
    private final EmployeeServiceImp employeeServiceImp;

    public Relative createRelative(RelativeDTO relativeDTO) {

        Relative relative = new Relative();
        relative.setFullName(relativeDTO.getFullName());
        relative.setGender(relativeDTO.getGender());
        relative.setPhoneNumber(relativeDTO.getPhoneNumber());
        relative.setRelationship(relativeDTO.getRelationship());
        Optional<Employee> employee = employeeServiceImp.getEmployeeById(relativeDTO.getEmployeeId());
        if (employee.isPresent())
            relative.setEmployee((employee.get()));
//        Employee employee = employeeService.getEmployeeById(relativeDTO.getEmployeeId()).orElseThrow();
//        return relativeRepository.save(relative);
        return relativeRepository.save(relative);
    }

    public List<Relative> getAllRelative() {
        return relativeRepository.findAll();
    }

    public Optional<Relative> getRelativeById(Long relativeId) {
        return relativeRepository.findById(relativeId);
    }

    public Relative updateRelative(Long relativeId, RelativeDTO relativeDTO) {
        Optional<Relative> relative = relativeRepository.findById(relativeId);
        Relative updateRelative = new Relative();

        updateRelative.setFullName(relativeDTO.getFullName());
        updateRelative.setGender(relativeDTO.getGender());
        updateRelative.setPhoneNumber(relativeDTO.getPhoneNumber());
        updateRelative.setRelationship(relativeDTO.getRelationship());

        Optional<Employee> employee = employeeServiceImp.getEmployeeById(relativeDTO.getEmployeeId());
        if (employee.isPresent())
            updateRelative.setEmployee((employee.get()));

        return relativeRepository.save(updateRelative);
    }

    public void deleteRelativeById(Long relativeId) {
        relativeRepository.deleteById(relativeId);
    }

    public List<Relative> getRelativeByFullName(String name){
        return relativeRepository.findByFullName(name);
    }

    public List<Relative> getRelativeByFullNameOrderByGenderDesc(String fullName){
        return  relativeRepository.findByFullNameOrderByGenderDesc(fullName);
    }

    public List<Relative> getRelativeByFullNameContaining(String name){
        return relativeRepository.findByFullNameContaining(name);
    }

    public List<Relative> getRelativeByGenderNot(Gender gender){
        return relativeRepository.findByGenderNot(gender);
    }
}
