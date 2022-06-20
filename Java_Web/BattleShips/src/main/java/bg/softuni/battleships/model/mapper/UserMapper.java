package bg.softuni.battleships.model.mapper;


import bg.softuni.battleships.model.DTO.RegisterDTO;
import bg.softuni.battleships.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User mapFromRegDto(RegisterDTO registerDTO);
}