package bg.softuni.exam.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "styles")
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private StyleName styleName;

    private String description;

    public Style() {
    }

    public Style(StyleName styleName) {
        this.styleName = styleName;
    }

    public long getId() {
        return id;
    }

    public Style setId(long id) {
        this.id = id;
        return this;
    }

    public StyleName getStyleName() {
        return styleName;
    }

    public Style setStyleName(StyleName styleName) {
        this.styleName = styleName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Style setDescription(String description) {
        this.description = description;
        return this;
    }
}
