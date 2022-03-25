package com.example.football.models.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class TeamImportDTO implements Serializable {

    @Length(min = 3)
    @NotNull
    private String name;

    @Length(min = 3)
    @NotNull
    private String stadiumName;

    @Min(1000)
    @NotNull
    private int fanBase;

    @Length(min = 10)
    @NotNull
    private String history;

    @Length(min = 2)
    @NotNull
    private String townName;

    public String getName() {
        return name;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public int getFanBase() {
        return fanBase;
    }

    public String getHistory() {
        return history;
    }

    public String getTownName() {
        return townName;
    }
}
