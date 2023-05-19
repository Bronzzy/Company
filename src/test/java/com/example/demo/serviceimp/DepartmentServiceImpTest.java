package com.example.demo.serviceimp;

import com.example.demo.serviceimp.dto.DepartmentStatistic;
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
class DepartmentServiceImpTest {

    @Autowired
    private DepartmentServiceImp departmentServiceImp;
    @Test
    void getDepartmentStatistics() {
        List<DepartmentStatistic> temp = departmentServiceImp.getDepartmentStatistics();
        temp.forEach(System.out::println);
    }
}