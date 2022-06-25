package bg.softuni.exam.web;

import bg.softuni.exam.model.dto.SongDTO;
import bg.softuni.exam.model.entity.StyleName;
import bg.softuni.exam.service.AuthService;
import bg.softuni.exam.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

    private final AuthService authService;
    private SongService songService;

    public HomeController(AuthService authService, SongService songService) {
        this.authService = authService;
        this.songService = songService;
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

        List<SongDTO> popSongs = this.songService.getSongsByStyleName(StyleName.POP);
        List<SongDTO> rockSongs = this.songService.getSongsByStyleName(StyleName.ROCK);
        List<SongDTO> jazzSongs = this.songService.getSongsByStyleName(StyleName.JAZZ);
        List<SongDTO> playList = this.songService.getPlayList();

        model.addAttribute("popSongs", popSongs);
        model.addAttribute("rockSongs", rockSongs);
        model.addAttribute("jazzSongs", jazzSongs);
        model.addAttribute("playList", playList);
        model.addAttribute("totalTime", this.songService.getPlaylistTime());

        return "home";
    }

    @GetMapping("/home/{id}")
    public String add(@PathVariable("id") long songId) {
        if (!this.authService.hasSession()) {
            return "redirect:/login";
        }

        this.songService.addToPlaylist(songId);

        return "redirect:/home";
    }

    @GetMapping("/home/clear")
    public String clear() {
        if (!this.authService.hasSession()) {
            return "redirect:/login";
        }

        this.songService.clearPlaylist();

        return "redirect:/home";
    }
}
