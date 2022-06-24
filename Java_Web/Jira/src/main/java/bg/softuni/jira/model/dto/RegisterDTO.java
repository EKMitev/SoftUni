package bg.softuni.jira.model.dto;


import bg.softuni.jira.model.validation.EmailNotUsed;
import bg.softuni.jira.model.validation.FieldMatch;
import bg.softuni.jira.model.validation.UsernameNotUsed;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message = "Passwords do not match.")
public class RegisterDTO {

    @UsernameNotUsed(message = "Username already used.")
    @NotBlank(message = "Username cannot be blank.")
    @Size(min = 5, max = 20, message = "Username length must be between 5 and 20 characters.")
    private String username;

    @EmailNotUsed(message = "Email already used.")
    @Email(message = "Enter valid email address.")
    @NotNull
    private String email;

    @NotBlank(message = "Cannot be blank.")
    @Size(min = 3, message = "Password length must be at least 3 characters long.")
    private String password;

    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public RegisterDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public RegisterDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
