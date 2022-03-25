package com.example.demo.service;

import com.example.demo.entity.dto.UsersWithBuyerDTO;

import java.util.List;

public interface UserService {
    List<UsersWithBuyerDTO> findUsersWithSoldProducts();
}
