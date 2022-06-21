package bg.softuni.battleships.web;

import bg.softuni.battleships.model.DTO.FireModel;
import bg.softuni.battleships.model.DTO.ShipDTO;
import bg.softuni.battleships.service.ShipService;
import bg.softuni.battleships.session.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final ShipService shipService;

    public HomeController(CurrentUser currentUser, ShipService shipService) {
        this.currentUser = currentUser;
        this.shipService = shipService;
    }

    @GetMapping("/")
    public String indexPage() {
        if (this.currentUser.isLogged()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (!this.currentUser.isLogged()) {
            return "redirect:/login";
        }

        List<ShipDTO> allShips = this.shipService.getAll();
        List<ShipDTO> userShips = this.shipService.getUserShips();
        List<ShipDTO> enemyShips = this.shipService.getEnemyShips();

        model.addAttribute("allShips", allShips);
        model.addAttribute("userShips", userShips);
        model.addAttribute("enemyShips", enemyShips);

        return "home";
    }

    @ModelAttribute("fireModel")
    public FireModel initFireModel(){
        return new FireModel();
    }

    @PostMapping("/home")
    public String submitFire(FireModel fireModel) {
        if (fireModel.getAttackerId() == 0 || fireModel.getDefenderId() == 0) {
            return "redirect:/home";
        }

       this.shipService.fire(fireModel);

        return "redirect:/home";
    }
}
