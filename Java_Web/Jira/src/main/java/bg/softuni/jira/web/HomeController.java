package bg.softuni.jira.web;

import bg.softuni.jira.model.dto.TaskDTO;
import bg.softuni.jira.service.AuthService;
import bg.softuni.jira.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

    private final TaskService taskService;
    private final AuthService authService;

    public HomeController(TaskService taskService, AuthService authService) {
        this.taskService = taskService;
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

        List<TaskDTO> allTasks = this.taskService.getAllTasks();

        model.addAttribute("tasks", allTasks);
        return "home";
    }

    @GetMapping("/home/{id}")
    public String progress(@PathVariable long id) {
        if (!this.authService.hasSession()) {
            return "redirect:/login";
        }

        this.taskService.progress(id);

        return "redirect:/home";
    }
}
