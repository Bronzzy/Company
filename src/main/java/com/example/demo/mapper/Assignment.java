package com.example.demo.mapper;

import com.example.demo.serviceimp.dto.AssignmentDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Assignment {
    AssignmentDTO toDTO(Assignment assignment);
    List<Assignment> toDTOs(List<Assignment> assignments);
}
