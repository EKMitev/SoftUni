package bg.softuni.battleships.model.DTO;

import bg.softuni.battleships.model.validation.EmailNotUsed;
import bg.softuni.battleships.model.validation.FieldMatch;
import bg.softuni.battleships.model.validation.UsernameNotUsed;

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
    @NotBlank
    @Size(min = 3, max = 10, message = "Username length must be between 3 and 20 characters.")
    private String username;

    @NotBlank
    @Size(min = 5, max = 20)
    private String fullName;

    @EmailNotUsed(message = "Email already used.")
    @Email(message = "Enter valid email address.")
    @NotBlank(message = "Enter valid email address.")
    private String email;

    @NotBlank
    @Size(min = 3)
    private String password;

    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public RegisterDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public RegisterDTO setFullName(String fullName) {
        this.fullName = fullName;
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
