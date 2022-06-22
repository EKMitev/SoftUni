package bg.softuni.coffeeshop.web;

import bg.softuni.coffeeshop.model.dto.OrderDTO;
import bg.softuni.coffeeshop.model.dto.UserDTO;
import bg.softuni.coffeeshop.service.AuthService;
import bg.softuni.coffeeshop.service.OrderService;
import bg.softuni.coffeeshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    private final OrderService orderService;
    private final UserService userService;
    private final AuthService authService;

    public HomeController(OrderService orderService, UserService userService, AuthService authService) {
        this.orderService = orderService;
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping("/")
    public String index() {
        if (this.authService.hasSession()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (!this.authService.hasSession()) {
            return "redirect:/login";
        }

        List<OrderDTO> allOrders = this.orderService.getAllOrders();
        List<UserDTO> allEmployees = this.userService.getAllUsers();

        model.addAttribute("totalTime", this.orderService.getTime());
        model.addAttribute("orders", allOrders);
        model.addAttribute("employees", allEmployees);

        return "home";
    }

    @GetMapping("/home/ready")
    public String ready(@RequestParam("id") long orderId) {
        this.orderService.completeOrder(orderId);

        return "redirect:/home";
    }
}
