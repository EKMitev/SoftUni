package bg.softuni.exam.model.mapper;

import bg.softuni.exam.model.dto.AddSongDTO;
import bg.softuni.exam.model.dto.SongDTO;
import bg.softuni.exam.model.entity.Song;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SongMapper {
    Song mapFromDto(AddSongDTO addSongDTO);

    SongDTO mapFromEntity(Song song);
}
