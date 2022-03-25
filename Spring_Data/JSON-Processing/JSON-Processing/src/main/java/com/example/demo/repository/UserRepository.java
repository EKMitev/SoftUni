package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u" +
            " JOIN u.soldProducts p" +
            " WHERE p.buyer IS NOT NULL" +
            " ORDER BY u.lastName ASC, u.firstName ASC")
    List<User> findUsersWithSoldProducts();
}
