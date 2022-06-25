package bg.softuni.exam.model.dto;

import bg.softuni.exam.model.entity.StyleName;
import bg.softuni.exam.model.validation.NameNotUsed;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class AddSongDTO {

    @NameNotUsed(message = "Performer already used.")
    @NotBlank(message = "")
    @Size(min = 3, max = 20, message = "Performer name length must be between 3 and 20 characters.")
    private String performer;

    @NotBlank(message = "")
    @Size(min = 3, max = 20, message = "Title length must be between 3 and 20 characters.")
    private String title;

    @Positive
    private int duration;

    @PastOrPresent(message = "Release date cannot be in the future.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Enter a valid date")
    private LocalDate releaseDate;

    @NotNull
    private StyleName style;


    public String getPerformer() {
        return performer;
    }

    public AddSongDTO setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public AddSongDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public AddSongDTO setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public AddSongDTO setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public StyleName getStyle() {
        return style;
    }

    public AddSongDTO setStyle(StyleName style) {
        this.style = style;
        return this;
    }
}
