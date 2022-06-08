package com.example.demo.web;

import com.example.demo.models.dto.UserLoginDTO;
import com.example.demo.models.dto.UserRegDTO;
import com.example.demo.service.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class AuthController {
    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @ModelAttribute("userModel")
    public UserRegDTO initUserModel() {
        return new UserRegDTO();
    }

    @GetMapping("/register")
    public String register() {
        return "auth-register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegDTO userModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);
            return "redirect:/users/register";
        }

        authenticationService.register(userModel);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "auth-login";
    }

    @PostMapping("/login")
    public String login(UserLoginDTO userLoginDTO,
                        RedirectAttributes redirectAttributes) {
        boolean logged = this.authenticationService.login(userLoginDTO);

        if(!logged) {
            redirectAttributes.addFlashAttribute("err", true);
            return "redirect:/users/login";
        }

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        this.authenticationService.logout();
        return "redirect:/";
    }
}
