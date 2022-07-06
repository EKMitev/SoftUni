package com.example.demo.models.mapper;

import com.example.demo.models.dto.UserRegDTO;
import com.example.demo.models.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "active", constant = "true")
    User mapDtoToUser(UserRegDTO regDTO);

}
