package com.example.demo.service;

import com.example.demo.CurrentUser;
import com.example.demo.models.mapper.UserMapper;
import com.example.demo.models.entity.User;
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
    private final UserMapper userMapper;

    public AuthenticationService(CurrentUser currentUser,
                                 UserRepository userRepository, UserMapper userMapper) {
        this.currentUser = currentUser;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void register(UserRegDTO userRegDTO) {
        User user = this.userMapper.mapDtoToUser(userRegDTO);

        this.userRepository.save(user);
        login(user);
    }

    public boolean login(UserLoginDTO userLoginDTO) {
        Optional<User> userOpt = this.userRepository.findByUsername(userLoginDTO.getUsername());

        if (userOpt.isEmpty()) {
            LOGGER.info("Invalid user {}", userLoginDTO.getUsername());
            return false;
        }

       if (userLoginDTO.getPassword().equals(userOpt.get().getPassword())) {
           login(userOpt.get());
           return true;
       }
       return false;
    }

    private void login(User user) {
        this.currentUser
                .setLoggedIn(true)
                .setName(user.getFirstName() + " " + user.getLastName())
                .setUserName(user.getUsername());

        System.out.println(this.currentUser);
    }

    public void logout() {
        this.currentUser.clear();
    }
}
