package com.example.demo.models.dto;

import com.example.demo.models.enums.Engine;
import com.example.demo.models.enums.Transmission;

import java.math.BigDecimal;

public class OfferDTO {

    private String imageURL;
    private Integer year;
    private String brand;
    private String model;
    private Integer mileage;
    private BigDecimal price;
    private Engine engine;
    private Transmission transmission;

    public String getImageURL() {
        return imageURL;
    }

    public OfferDTO setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public OfferDTO setYear(Integer year) {
        this.year = year;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public OfferDTO setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public OfferDTO setModel(String model) {
        this.model = model;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public OfferDTO setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Engine getEngine() {
        return engine;
    }

    public OfferDTO setEngine(Engine engine) {
        this.engine = engine;
        return this;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public OfferDTO setTransmission(Transmission transmission) {
        this.transmission = transmission;
        return this;
    }

    public String getOfferPrimeData() {
        return this.year + " " + this.brand + " " + this.model;
    }
}
