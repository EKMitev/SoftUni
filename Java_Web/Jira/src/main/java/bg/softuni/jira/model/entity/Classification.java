package bg.softuni.jira.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "classifications")
public class Classification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClassificationName name;

    private String description;

    public Classification() {
    }

    public Classification(ClassificationName name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public Classification setId(long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Classification setDescription(String description) {
        this.description = description;
        return this;
    }

    public ClassificationName getName() {
        return name;
    }

    public Classification setName(ClassificationName name) {
        this.name = name;
        return this;
    }
}
