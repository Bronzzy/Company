package com.example.demo.mapper;

import com.example.demo.entity.Relative;
import com.example.demo.serviceimp.dto.RelativeDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RelativeMapper {

    RelativeDTO toDTo(Relative relative);

    List<RelativeDTO> toDtos(List<Relative> relatives);
}
