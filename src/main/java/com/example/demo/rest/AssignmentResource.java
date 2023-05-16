package com.example.demo.rest;

import com.example.demo.entity.Assignment;
import com.example.demo.serviceimp.AssignmentServiceImp;
import com.example.demo.serviceimp.dto.AssignmentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AssignmentResource implements AssignmentAPI{

    private final AssignmentServiceImp assignmentServiceImp;
    @Override
    public ResponseEntity<Assignment> createAssignment(AssignmentDTO assignmentDTO) {
        Assignment assignment = assignmentServiceImp.createAssignment((assignmentDTO));
        return ResponseEntity.created(URI.create("/api/assignments" + assignment.getAssignmentId())).body(assignment);
    }

    @Override
    public ResponseEntity<List<Assignment>> getAllAssignment() {
        return ResponseEntity.ok(assignmentServiceImp.getAllAssignment());
    }

    @Override
    public ResponseEntity<Optional<Assignment>> getAssignmentById(Long assignmentId) {
        return ResponseEntity.ok(assignmentServiceImp.getAssignmentById(assignmentId));
    }

    @Override
    public ResponseEntity<Assignment> updateAssignment(Long assignmentId, AssignmentDTO assignmentDTO) {
        Assignment assignment = assignmentServiceImp.updateAssignment(assignmentId,assignmentDTO);
        return ResponseEntity.created(URI.create("/api/assignments" + assignment.getAssignmentId())).body(assignment);
    }

    @Override
    public ResponseEntity<Assignment> deleteAssignment(Long assignmentId) {
        return ResponseEntity.noContent().build();
    }
}
