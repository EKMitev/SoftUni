package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.entity.dto.UsersWithBuyerDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

        this.mapper = new ModelMapper();
    }

    @Override
    @Transactional
    public List<UsersWithBuyerDTO> findUsersWithSoldProducts() {
        List<User> users = this.userRepository.findUsersWithSoldProducts();

        return users.stream()
                .map(u -> this.mapper.map(u, UsersWithBuyerDTO.class))
                .collect(Collectors.toList());
    }
}
