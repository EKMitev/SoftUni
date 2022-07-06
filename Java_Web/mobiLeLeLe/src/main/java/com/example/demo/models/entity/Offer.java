package com.example.demo.models.entity;

import com.example.demo.models.enums.Engine;
import com.example.demo.models.enums.Transmission;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Engine engine;

    @Column(name = "image_url", columnDefinition = "text")
    private String imageURL;

    private int mileage;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Transmission transmission;

    private int year;

    private Instant created = Instant.now();

    private Instant modified =Instant.now();

    @ManyToOne(optional = false)
    private Model model;

    @ManyToOne(optional = false)
    private User seller;

    public Offer() {
    }

    public long getId() {
        return id;
    }

    public Offer setId(long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Offer setDescription(String description) {
        this.description = description;
        return this;
    }

    public Engine getEngine() {
        return engine;
    }

    public Offer setEngine(Engine engine) {
        this.engine = engine;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public Offer setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public int getMileage() {
        return mileage;
    }

    public Offer setMileage(int mileage) {
        this.mileage = mileage;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Offer setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public Offer setTransmission(Transmission transmission) {
        this.transmission = transmission;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Offer setYear(int year) {
        this.year = year;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public Offer setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public Instant getModified() {
        return modified;
    }

    public Offer setModified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public Model getModel() {
        return model;
    }

    public Offer setModel(Model model) {
        this.model = model;
        return this;
    }

    public User getSeller() {
        return seller;
    }

    public Offer setSeller(User seller) {
        this.seller = seller;
        return this;
    }
}
