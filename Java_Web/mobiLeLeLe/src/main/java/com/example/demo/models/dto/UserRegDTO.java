package com.example.demo.models.dto;

import com.example.demo.models.validation.FieldMatch;
import com.example.demo.models.validation.UsernameNotUsed;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@FieldMatch(first = "password", second = "confirmPassword", message = "Passwords do not match.")
public class UserRegDTO {

    @UsernameNotUsed(message = "Username already used.")
    @NotEmpty(message = "Username should be provided.")
    @Size(min = 2, max = 20, message = "Username should be between 2 and 20 characters.")
    private String username;

    @NotEmpty
    @Size(min = 2, max = 20)
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 20)
    private String lastName;

    @NotEmpty
    @Size(min = 5)
    private String password;
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
