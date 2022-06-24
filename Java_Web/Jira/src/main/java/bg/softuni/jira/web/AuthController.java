package bg.softuni.jira.web;

import bg.softuni.jira.model.dto.LoginDTO;
import bg.softuni.jira.model.dto.RegisterDTO;
import bg.softuni.jira.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ModelAttribute("registerDTO")
    public RegisterDTO initRegisterDto() {
        return new RegisterDTO();
    }

    @GetMapping("/register")
    public String register() {
        if (this.authService.hasSession()) {
            return "redirect:/home";
        }

        return "register";
    }

    @PostMapping("/register")
    public String submitRegister(@Valid RegisterDTO registerDTO,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (this.authService.hasSession()) {
            return "redirect:/home";
        }


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerDTO", registerDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", bindingResult);

            return "redirect:/register";
        }

        System.out.println();

        this.authService.register(registerDTO);

        return "redirect:/home";
    }

    @ModelAttribute("loginDTO")
    public LoginDTO initLoginDTO() {
        return new LoginDTO();
    }

    @GetMapping("/login")
    public String login() {
        if (this.authService.hasSession()) {
            return "redirect:/home";
        }

        return "login";
    }

    @PostMapping("/login")
    public String login(LoginDTO loginDTO, RedirectAttributes redirectAttributes) {
        if (this.authService.hasSession()) {
            return "redirect:/home";
        }


        if (!this.authService.login(loginDTO)) {
            redirectAttributes.addFlashAttribute("invalidCredentials", true);
            return "redirect:/login";
        }

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        this.authService.logout();
        return "redirect:/";
    }
}
