package com.example.demo.models.dto;

import com.example.demo.models.enums.Engine;
import com.example.demo.models.enums.Transmission;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class AddOfferDTO {

    @NotNull
    @Positive
    private Long modelId;

    @Positive
    @NotNull
    private BigDecimal price;

    @NotNull
    private Engine engine;

    @NotNull
    private Transmission transmission;

    @NotEmpty
    private String imageUrl;

    @Min(1900)
    private int year;

    @Positive
    private int mileage;

    @NotEmpty
    private String description;

    public Long getModelId() {
        return modelId;
    }

    public AddOfferDTO setModelId(Long modelId) {
        this.modelId = modelId;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AddOfferDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Engine getEngine() {
        return engine;
    }

    public AddOfferDTO setEngine(Engine engine) {
        this.engine = engine;
        return this;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public AddOfferDTO setTransmission(Transmission transmission) {
        this.transmission = transmission;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AddOfferDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public int getYear() {
        return year;
    }

    public AddOfferDTO setYear(int year) {
        this.year = year;
        return this;
    }

    public int getMileage() {
        return mileage;
    }

    public AddOfferDTO setMileage(int mileage) {
        this.mileage = mileage;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddOfferDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
