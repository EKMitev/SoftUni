package com.example.demo.web;

import com.example.demo.models.dto.UserLoginDTO;
import com.example.demo.models.dto.UserRegDTO;
import com.example.demo.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/register")
    public String register() {
        return "auth-register";
    }

    @PostMapping("/register")
    public String register(UserRegDTO userRegDTO) {
        authService.register(userRegDTO);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "auth-login";
    }

    @PostMapping("/login")
    public String login(UserLoginDTO userLoginDTO) {
        this.authService.login(userLoginDTO);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        this.authService.logout();
        return "redirect:/";
    }
}
