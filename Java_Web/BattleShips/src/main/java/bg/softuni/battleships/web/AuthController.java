package bg.softuni.battleships.web;

import bg.softuni.battleships.model.DTO.LoginDTO;
import bg.softuni.battleships.model.DTO.RegisterDTO;
import bg.softuni.battleships.service.AuthService;
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

    @ModelAttribute("registerModel")
    private RegisterDTO initRegisterModel() {
        return new RegisterDTO();
    }

    @GetMapping("/register")
    public String submitRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid RegisterDTO registerModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerModel", registerModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerModel", bindingResult);
            return "redirect:/register";
        }

        this.authService.register(registerModel);

        System.out.println("here");
        return "home";
    }

    @ModelAttribute("loginModel")
    private LoginDTO initLoginModel() {
        return new LoginDTO();
    }

    @GetMapping("/login")
    public String login() {
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginDTO loginModel,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginModel", loginModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginModel", bindingResult);

            return "redirect:/login";
        }

        if (!this.authService.login(loginModel)) {
            redirectAttributes.addFlashAttribute("invalidCredentials", true);
            return "redirect:/login";
        }

        return "redirect:/home";
    }
}
