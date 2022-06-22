package bg.softuni.coffeeshop.service;

import bg.softuni.coffeeshop.model.dto.LoginDTO;
import bg.softuni.coffeeshop.model.dto.RegisterDTO;
import bg.softuni.coffeeshop.model.entity.User;
import bg.softuni.coffeeshop.model.mapper.UserMapper;
import bg.softuni.coffeeshop.repository.UserRepository;
import bg.softuni.coffeeshop.session.CurrentUser;
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
        Optional<User> optional = this.userRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        if(optional.isEmpty()) {
            return false;
        }

        login(optional.get());
        return true;
    }

    public void logout() {
        this.currentUser.clear();
    }

    public boolean isLogged() {
        return this.currentUser.isLogged();
    }

    private void login(User user) {
        this.currentUser.setLogged(user);
    }
}
