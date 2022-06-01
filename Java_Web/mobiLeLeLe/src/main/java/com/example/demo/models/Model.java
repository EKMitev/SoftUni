package com.example.demo.models;

import com.example.demo.models.enums.Category;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "models")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "image_url")
    private String imageURL;

    @Column(name = "start_year")
    private int startYear;

    @Column(name = "end_year")
    private int endYear;

    private Instant created;

    private Instant modified;

    @ManyToOne(optional = false)
    private Brand brand;

    public Model() {
    }

    public long getId() {
        return id;
    }

    public Model setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Model setName(String name) {
        this.name = name;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Model setCategory(Category category) {
        this.category = category;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public Model setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public int getStartYear() {
        return startYear;
    }

    public Model setStartYear(int startYear) {
        this.startYear = startYear;
        return this;
    }

    public int getEndYear() {
        return endYear;
    }

    public Model setEndYear(int endYear) {
        this.endYear = endYear;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public Model setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public Instant getModified() {
        return modified;
    }

    public Model setModified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public Brand getBrand() {
        return brand;
    }

    public Model setBrand(Brand brand) {
        this.brand = brand;
        return this;
    }
}
