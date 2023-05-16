package com.example.demo.rest;

import com.example.demo.entity.Gender;
import com.example.demo.entity.Relative;
import com.example.demo.serviceimp.dto.RelativeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/relatives")
public interface RelativeAPI {
    @PostMapping
    ResponseEntity<Relative> createRelative(@RequestBody RelativeDTO relativeDTO);

    @GetMapping
    ResponseEntity<List<Relative>> getAllRelative();

    @GetMapping("/{relativeId}")
    ResponseEntity<Optional<Relative>> getRelativeById(@PathVariable("relativeId") Long relativeId);

    @PutMapping("/{relativeId}")
    ResponseEntity<Relative> updateRelative(@PathVariable("relativeId") Long relativeId,
                                            @RequestBody RelativeDTO relativeDTO);

    @DeleteMapping("/{relativeId}")
    ResponseEntity<Void> deleteRelativeById(@PathVariable("relativeId") Long relativeId);

    @GetMapping("/getrelativebyfullname")
    ResponseEntity<List<Relative>> getRelativeByFullName(@RequestParam("fullName") String name);

    @GetMapping("/getrelativebyfullnameorderbygender")
    ResponseEntity<List<Relative>> getRelativeByFullNameOrderByGenderDesc(@RequestParam("fullName") String name);

    @GetMapping("/getrelativebyfullnamecontaining")
    ResponseEntity<List<Relative>> getRelativeByFullNameContaining(@RequestParam("fullName") String name);

    @GetMapping("/getrelativebygendernot")
    ResponseEntity<List<Relative>> getRelativeByGenderNot(@RequestParam("gender") Gender gender);
}
