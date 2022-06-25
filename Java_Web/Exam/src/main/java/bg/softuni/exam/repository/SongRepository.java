package bg.softuni.exam.repository;

import bg.softuni.exam.model.entity.Song;
import bg.softuni.exam.model.entity.Style;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {

    Optional<Song> findByPerformer(String performer);

    List<Song> getAllByStyle(Style style);

}
