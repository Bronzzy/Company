package com.example.demo.serviceimp;

import com.example.demo.serviceimp.dto.EmployeeDTO;
import com.example.demo.serviceimp.dto.RelativeDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class RelativeServiceTest {

    @Autowired
    private RelativeService relativeService;

    @Test
    void getEmployeeWithFatherRelationship() {
        List<RelativeDTO> tempList = relativeService.getEmployeeWithFatherRelationship();
        tempList.forEach(System.out::println);
    }
    @Test
    void testGetEmployeeWithMotherRelationship() {
        List<RelativeDTO> tempList = relativeService.getEmployeeWithMotherRelationship();
        tempList.forEach(System.out::println);
    }
    @Test
    void getEmployeeWithOtherRelationship() {
        List<RelativeDTO> tempList = relativeService.getEmployeeWithOtherRelationship();
        tempList.forEach(System.out::println);
    }
}