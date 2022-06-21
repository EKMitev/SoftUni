package bg.softuni.battleships.web;

import bg.softuni.battleships.session.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CurrentUser currentUser;

    public HomeController(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @GetMapping("/")
    public String indexPage() {
        if (this.currentUser.isLogged()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String home() {
        if (!this.currentUser.isLogged()) {
            return "redirect:/login";
        }

        return "home";
    }
}
