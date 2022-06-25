package bg.softuni.exam.service;

import bg.softuni.exam.model.dto.AddSongDTO;
import bg.softuni.exam.model.dto.SongDTO;
import bg.softuni.exam.model.entity.Song;
import bg.softuni.exam.model.entity.Style;
import bg.softuni.exam.model.entity.StyleName;
import bg.softuni.exam.model.entity.User;
import bg.softuni.exam.model.mapper.SongMapper;
import bg.softuni.exam.repository.SongRepository;
import bg.softuni.exam.repository.StyleRepository;
import bg.softuni.exam.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    private final StyleRepository styleRepository;
    private final SongMapper songMapper;
    private final SongRepository songRepository;
    private final UserRepository userRepository;
    private final AuthService authService;

    public SongService(StyleRepository styleRepository, SongMapper songMapper, SongRepository songRepository, UserRepository userRepository, AuthService authService) {
        this.styleRepository = styleRepository;
        this.songMapper = songMapper;
        this.songRepository = songRepository;
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @Transactional
    public void add(AddSongDTO addSongDTO) {
        StyleName styleName = addSongDTO.getStyle();
        Style style = this.styleRepository.getByStyleName(styleName);


        Song newSong = this.songMapper.mapFromDto(addSongDTO)
                .setStyle(style);


        this.songRepository.save(newSong);
    }

    public List<SongDTO> getSongsByStyleName(StyleName styleName) {
        Style style = this.styleRepository.getByStyleName(styleName);

        return this.songRepository.getAllByStyle(style)
                .stream()
                .map(this.songMapper::mapFromEntity)
                .toList();
    }

    public List<SongDTO> getPlayList() {
        long id = this.authService.getCurrentUserId();
        User user = this.userRepository.getById(id);

        return user.getPlaylist()
                .stream()
                .map(this.songMapper::mapFromEntity)
                .toList();
    }

    public String getPlaylistTime() {
        long id = this.authService.getCurrentUserId();
        User user = this.userRepository.getById(id);

        int timeInSeconds = user.getPlaylist()
                .stream()
                .mapToInt(Song::getDuration)
                .sum();

        int mins = timeInSeconds / 60;
        int secs = timeInSeconds % 60;

        return String.format("%02d:%02d min. total", mins, secs);
    }

    public void addToPlaylist(long songId) {
        long id = this.authService.getCurrentUserId();
        User user = this.userRepository.getById(id);

        Song song = this.songRepository.getById(songId);

        user.getPlaylist().add(song);
        this.userRepository.save(user);
    }

    @Transactional
    public void clearPlaylist() {
        long id = this.authService.getCurrentUserId();
        User user = this.userRepository.getById(id);

        this.songRepository.deleteAll(user.getPlaylist());

        user.getPlaylist().clear();
    }
}
