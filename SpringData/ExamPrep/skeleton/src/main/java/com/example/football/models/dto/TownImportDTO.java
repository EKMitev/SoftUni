package com.example.football.models.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

public class TownImportDTO implements Serializable {

    @Length(min = 2)
    @NotNull
    private String name;

    @Positive
    private int population;

    @Length(min = 10)
    @NotNull
    private String travelGuide;

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public String getTravelGuide() {
        return travelGuide;
    }
}
