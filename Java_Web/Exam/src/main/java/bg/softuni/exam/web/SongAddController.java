package bg.softuni.exam.web;

import bg.softuni.exam.model.dto.AddSongDTO;
import bg.softuni.exam.service.AuthService;
import bg.softuni.exam.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class SongAddController {

    private final SongService songService;
    private final AuthService authService;

    public SongAddController(SongService songService, AuthService authService) {
        this.songService = songService;
        this.authService = authService;
    }

    @ModelAttribute("addSongDTO")
    public AddSongDTO initAddSongDto() {
        return new AddSongDTO();
    }

    @GetMapping("/songs/add")
    public String add() {
        if (!this.authService.hasSession()) {
            return "redirect:/login";
        }

        return "/song-add";
    }

    @PostMapping("/songs/add")
    public String add(@Valid AddSongDTO addSongDTO,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes) {
        if (!this.authService.hasSession()) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addSongDTO", addSongDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addSongDTO", bindingResult);

            return "redirect:/songs/add";
        }

        this.songService.add(addSongDTO);

        return "redirect:/home";
    }
}
