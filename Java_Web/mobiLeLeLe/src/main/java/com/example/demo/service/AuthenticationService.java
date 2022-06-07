package com.example.demo.service;

import com.example.demo.CurrentUser;
import com.example.demo.models.User;
import com.example.demo.models.dto.UserLoginDTO;
import com.example.demo.models.dto.UserRegDTO;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);

    private final CurrentUser currentUser;

    private final UserRepository userRepository;

    public AuthenticationService(CurrentUser currentUser,
                                 UserRepository userRepository) {
        this.currentUser = currentUser;
        this.userRepository = userRepository;
    }

    public void register(UserRegDTO userRegDTO) {
        User user = new User()
                .setActive(true)
                .setUsername(userRegDTO.getUsername())
                .setFirstName(userRegDTO.getFirstName())
                .setLastName(userRegDTO.getLastName())
                .setPassword(userRegDTO.getPassword());

        this.userRepository.save(user);
        login(user);
    }

    public void login(UserLoginDTO userLoginDTO) {
        Optional<User> userOpt = this.userRepository.findByUsername(userLoginDTO.getUsername());

        if (userOpt.isEmpty()) {
            LOGGER.info("Invalid user {}", userLoginDTO.getUsername());
            return;
        }

       if (userLoginDTO.getPassword().equals(userOpt.get().getPassword())) {
           login(userOpt.get());
       }
    }

    private void login(User user) {
        this.currentUser
                .setLoggedIn(true)
                .setName(user.getFirstName() + " " + user.getLastName());
    }

    public void logout() {
        this.currentUser.clear();
    }
}
