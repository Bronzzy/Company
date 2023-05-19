package com.example.demo.serviceimp;

import com.example.demo.serviceimp.dto.CustomAssignmentEmployeeProject;
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
class AssignmentServiceImpTest {

    @Autowired
    private AssignmentServiceImp assignmentServiceImp;
    @Test
    void findProjectInArea() {
        String area = "Riyom";
        CustomAssignmentEmployeeProject getProject=assignmentServiceImp.findProjectInArea(area);
        System.out.println(getProject);
    }

    @Test
    void query6() {
        List<CustomAssignmentEmployeeProject> customAssignmentEmployeeProjectList = assignmentServiceImp.query6();
        customAssignmentEmployeeProjectList.forEach(System.out::println);
    }
}