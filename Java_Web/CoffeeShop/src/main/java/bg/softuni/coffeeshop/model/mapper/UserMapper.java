package bg.softuni.coffeeshop.model.mapper;


import bg.softuni.coffeeshop.model.dto.RegisterDTO;
import bg.softuni.coffeeshop.model.dto.UserDTO;
import bg.softuni.coffeeshop.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User mapFromRegDto(RegisterDTO registerDTO);

    UserDTO mapFromEntity(User user);
}