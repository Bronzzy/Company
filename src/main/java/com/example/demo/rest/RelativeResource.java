package com.example.demo.rest;

import com.example.demo.entity.Gender;
import com.example.demo.entity.Relative;
import com.example.demo.serviceimp.RelativeService;
import com.example.demo.serviceimp.dto.RelativeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class RelativeResource implements RelativeAPI {

    private final RelativeService relativeService;

    @Override
    public ResponseEntity<Relative> createRelative(RelativeDTO relativeDTO) {
        Relative relative = relativeService.createRelative(relativeDTO);
        return ResponseEntity.created(URI.create("/api/relatives" + relative.getRelativeId())).body(relative);
    }

    @Override
    public ResponseEntity<List<Relative>> getAll() {
        return ResponseEntity.ok(relativeService.getAll());
    }


    @Override
    public ResponseEntity<Optional<Relative>> getRelativeById(Long relativeId) {
        return ResponseEntity.ok(relativeService.getRelativeById(relativeId));
    }

    @Override
    public ResponseEntity<Relative> updateRelative(Long relativeId, RelativeDTO relativeDTO) {
        Relative relative = relativeService.updateRelative(relativeId, relativeDTO);
        return ResponseEntity.created(URI.create("/api/relatives" + relative.getRelativeId())).body(relative);
    }

    @Override
    public ResponseEntity<Void> deleteRelativeById(Long relativeId) {
        relativeService.deleteRelativeById(relativeId);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<List<Relative>> getRelativeByFullName(String name) {
        return ResponseEntity.ok(relativeService.getRelativeByFullName(name));
    }

    public ResponseEntity<List<Relative>> getRelativeByFullNameOrderByGenderDesc(String fullName) {
        return ResponseEntity.ok(relativeService.getRelativeByFullNameOrderByGenderDesc(fullName));
    }

    @Override
    public ResponseEntity<List<Relative>> getRelativeByFullNameContaining(String name) {
        return ResponseEntity.ok(relativeService.getRelativeByFullNameContaining(name));
    }

    @Override
    public ResponseEntity<List<Relative>> getRelativeByGenderNot(Gender gender) {
        return ResponseEntity.ok(relativeService.getRelativeByGenderNot(gender));
    }
}
