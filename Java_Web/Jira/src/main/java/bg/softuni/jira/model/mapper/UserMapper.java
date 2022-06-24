package bg.softuni.jira.model.mapper;


import bg.softuni.jira.model.dto.RegisterDTO;
import bg.softuni.jira.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User mapFromRegDto(RegisterDTO registerDTO);

}