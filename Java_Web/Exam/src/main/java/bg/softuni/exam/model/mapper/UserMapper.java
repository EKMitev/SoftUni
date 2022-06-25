package bg.softuni.exam.model.mapper;

import bg.softuni.exam.model.dto.RegisterDTO;
import bg.softuni.exam.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User mapFromRegDto(RegisterDTO registerDTO);
}
