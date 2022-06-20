package bg.softuni.battleships.service;

import bg.softuni.battleships.model.DTO.LoginDTO;
import bg.softuni.battleships.model.DTO.RegisterDTO;
import bg.softuni.battleships.model.entity.User;
import bg.softuni.battleships.model.mapper.UserMapper;
import bg.softuni.battleships.repository.UserRepository;
import bg.softuni.battleships.session.CurrentUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CurrentUser currenUser;

    public AuthService(UserRepository userRepository, UserMapper userMapper, CurrentUser currenUser) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.currenUser = currenUser;
    }

    public void register(RegisterDTO registerDTO) {
        User user = userMapper.mapFromRegDto(registerDTO);
        this.userRepository.save(user);

        login(user);
    }

    public boolean login(LoginDTO loginDTO) {
        Optional<User> optional = this.userRepository
                .findUserByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        if (optional.isEmpty()) {
            return false;
        }

        login(optional.get());
        return true;
    }

    private void login(User user){
        currenUser.setLogged(user);
    }

    public void logout() {
        currenUser.clear();
    }
}
