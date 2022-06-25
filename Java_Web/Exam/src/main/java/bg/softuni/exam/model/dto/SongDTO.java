package bg.softuni.exam.model.dto;

public class SongDTO {

    private long id;
    private String performer;
    private String title;
    private int duration;

    public String getPerformer() {
        return performer;
    }

    public long getId() {
        return id;
    }

    public SongDTO setId(long id) {
        this.id = id;
        return this;
    }

    public SongDTO setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SongDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public SongDTO setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    private String convertSecs() {
        int mins = this.duration / 60;
        int secs = this.duration % 60;

        return String.format("%02d:%02d", mins, secs);
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s min)", this.performer, this.title, this.convertSecs());
    }
}
