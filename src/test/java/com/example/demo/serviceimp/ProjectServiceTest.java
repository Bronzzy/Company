package com.example.demo.serviceimp;

import com.example.demo.serviceimp.dto.ProjectDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ProjectServiceTest {
    @Autowired
    private ProjectService projectService;

    @Test
    void getAllProjectAndDepartment() {
        List<ProjectDTO> tempList = projectService.getAllProjectAndDepartment();
        tempList.forEach(System.out::println);
    }
}