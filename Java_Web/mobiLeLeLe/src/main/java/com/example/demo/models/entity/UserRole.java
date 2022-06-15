package com.example.demo.models.entity;

import com.example.demo.models.enums.UserRoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    public UserRole() {
    }

    public long getId() {
        return id;
    }

    public UserRole setId(long id) {
        this.id = id;
        return this;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public UserRole setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
