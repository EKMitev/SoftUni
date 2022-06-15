package com.example.demo.models.mapper;

import com.example.demo.models.dto.ModelDTO;
import com.example.demo.models.entity.Model;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModelMapper {

    ModelDTO mapEntityToModelDto(Model model);
}
