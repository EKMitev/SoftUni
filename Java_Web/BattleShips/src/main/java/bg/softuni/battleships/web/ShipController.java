package bg.softuni.battleships.web;

import bg.softuni.battleships.model.DTO.ShipDTO;
import bg.softuni.battleships.service.ShipService;
import bg.softuni.battleships.session.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ShipController {

    private final CurrentUser currentUser;
    private final ShipService shipService;

    public ShipController(CurrentUser currentUser, ShipService shipService) {
        this.currentUser = currentUser;
        this.shipService = shipService;
    }

    @ModelAttribute("shipModel")
    public ShipDTO initShipModel() {
        return new ShipDTO();
    }

    @GetMapping("/ships/add")
    public String addShip() {
        if (!this.currentUser.isLogged()) {
            return "redirect:/login";
        }

        return "ship-add";
    }

    @PostMapping("/ships/add")
    public String addShip(@Valid ShipDTO shipModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("shipModel", shipModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.shipModel", bindingResult);
            return "redirect:/ships/add";
        }

        if (!this.shipService.addShip(shipModel)) {
            return "redirect:/login";
        }

        return "redirect:/home";
    }

}
