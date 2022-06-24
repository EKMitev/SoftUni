package bg.softuni.jira.service;

import bg.softuni.jira.Repository.UserRepository;
import bg.softuni.jira.model.dto.LoginDTO;
import bg.softuni.jira.model.dto.RegisterDTO;
import bg.softuni.jira.model.entity.User;
import bg.softuni.jira.model.mapper.UserMapper;
import bg.softuni.jira.session.CurrentUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;

    public AuthService(UserMapper userMapper, UserRepository userRepository, CurrentUser currentUser) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    public void register(RegisterDTO registerDTO) {
        User newUser = this.userMapper.mapFromRegDto(registerDTO);
        this.userRepository.save(newUser);

        login(newUser);
    }

    public boolean login(LoginDTO loginDTO) {
        Optional<User> optional =
                this.userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());

        if (optional.isEmpty()) {
            return false;
        }

        login(optional.get());
        return true;
    }

    public void logout() {
        this.currentUser.clear();
    }

    public boolean hasSession() {
        return this.currentUser.isLogged();
    }

    private void login(User user) {
        this.currentUser.setLogged(user);
    }

    public User getLoggedUser() {
        return this.userRepository.findByUsername(this.currentUser.getUsername()).get();
    }
}
