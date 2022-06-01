package com.example.demo.models;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    private Instant created;

    private Instant modified;

    public Brand() {
    }

    public long getId() {
        return id;
    }

    public Brand setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Brand setName(String name) {
        this.name = name;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public Brand setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public Instant getModified() {
        return modified;
    }

    public Brand setModified(Instant modified) {
        this.modified = modified;
        return this;
    }
}
