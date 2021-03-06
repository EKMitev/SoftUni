package bg.softuni.coffeeshop.web;

import bg.softuni.coffeeshop.model.dto.LoginDTO;
import bg.softuni.coffeeshop.model.dto.RegisterDTO;
import bg.softuni.coffeeshop.service.AuthService;
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
    public RegisterDTO initRegDTO() {
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

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerDTO", registerDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", bindingResult);

            return "redirect:/register";
        }

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
    public String login(@Valid LoginDTO loginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginDTO", loginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginDTO", bindingResult);

            return "redirect:/login";
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
