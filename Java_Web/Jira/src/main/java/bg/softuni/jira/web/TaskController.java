package bg.softuni.jira.web;

import bg.softuni.jira.model.dto.TaskAddDTO;
import bg.softuni.jira.service.AuthService;
import bg.softuni.jira.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class TaskController {

    private final TaskService taskService;
    private final AuthService authService;

    public TaskController(TaskService taskService, AuthService authService) {
        this.taskService = taskService;
        this.authService = authService;
    }

    @ModelAttribute("taskDTO")
    public TaskAddDTO initTaskDto() {
        return new TaskAddDTO();
    }

    @GetMapping("/tasks/add")
    public String add() {
        if (!this.authService.hasSession()) {
            return "redirect:/login";
        }

        return "add-task";
    }

    @PostMapping("/tasks/add")
    public String add(@Valid TaskAddDTO taskAddDTO,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes) {
        if (!this.authService.hasSession()) {
            return "redirect:/login";
        }


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("taskDTO", taskAddDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.taskDTO", bindingResult);

            return "redirect:/tasks/add";
        }

        this.taskService.addTask(taskAddDTO);

        return "redirect:/home";
    }
}
