package bg.softuni.coffeeshop.web;

import bg.softuni.coffeeshop.model.dto.OrderAddDTO;
import bg.softuni.coffeeshop.service.AuthService;
import bg.softuni.coffeeshop.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class OrderAddController {

    private final OrderService orderService;
    private final AuthService authService;

    public OrderAddController(OrderService orderService, AuthService authService) {
        this.orderService = orderService;
        this.authService = authService;
    }

    @ModelAttribute("orderAddDTO")
    public OrderAddDTO initOrderAddDto() {
        return new OrderAddDTO();
    }

    @GetMapping("/orders/add")
    public String add() {
        if (!this.authService.hasSession()) {
            return "redirect:/login";
        }

        return "/order-add";
    }

    @PostMapping("/orders/add")
    public String submitRegister(@Valid OrderAddDTO orderAddDTO,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("orderAddDTO", orderAddDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderAddDTO", bindingResult);

            return "redirect:/orders/add";
        }

        this.orderService.add(orderAddDTO);

        return "redirect:/home";
    }
}
