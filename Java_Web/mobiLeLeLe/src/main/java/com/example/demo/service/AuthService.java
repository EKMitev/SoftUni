package com.example.demo.service;

import com.example.demo.models.User;
import com.example.demo.models.dto.UserRegDTO;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
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
    }
}
