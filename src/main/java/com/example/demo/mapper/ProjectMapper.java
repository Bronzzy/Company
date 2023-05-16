package com.example.demo.mapper;

import com.example.demo.entity.Project;
import com.example.demo.serviceimp.dto.ProjectDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectDTO toDTO (Project project);
    List<ProjectDTO> toDTOs(List<Project> projects);
}
