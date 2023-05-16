package com.example.demo.rest;

import com.example.demo.entity.Assignment;
import com.example.demo.serviceimp.dto.AssignmentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RequestMapping("/api/assignments")
public interface AssignmentAPI {

    @PostMapping
    ResponseEntity<Assignment> createAssignment(@RequestBody AssignmentDTO assignmentDTO);

    @GetMapping
    ResponseEntity<List<Assignment>> getAllAssignment();

    @GetMapping("/{assignmentId}")
    ResponseEntity<Optional<Assignment>> getAssignmentById(@PathVariable("assignmentId") Long assignmentId);

    @PutMapping("/{assignmentId}")
    ResponseEntity<Assignment> updateAssignment(@PathVariable("assignment") Long assignmentId,
                                                @RequestBody AssignmentDTO assignmentDTO);

    @DeleteMapping("/{assignmentId}")
    ResponseEntity<Assignment> deleteAssignment(@PathVariable("assignment") Long assignmentId);
}
