package com.example.demo.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserRegDTO {

    @NotBlank
    @NotEmpty
    @Size(min = 2, max = 20)
    private String username;

    @NotBlank
    @NotEmpty
    @Size(min = 2, max = 20)
    private String firstName;

    @NotBlank
    @NotEmpty
    @Size(min = 2, max = 20)
    private String lastName;

    @NotBlank
    @NotEmpty
    @Size(min = 5)
    private String password;
    @NotBlank
    @NotEmpty
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public UserRegDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
