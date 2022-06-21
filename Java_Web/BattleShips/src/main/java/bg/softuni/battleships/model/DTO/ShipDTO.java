package bg.softuni.battleships.model.DTO;

import bg.softuni.battleships.model.entity.CategoryEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class ShipDTO {

    @NotBlank
    @Size(min = 2, max = 20)
    private String name;

    @Positive
    private long power;

    @Positive
    private long health;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate created;

    @PositiveOrZero
    private int category = -1;

    public String getName() {
        return name;
    }

    public ShipDTO setName(String name) {
        this.name = name;
        return this;
    }

    public long getPower() {
        return power;
    }

    public ShipDTO setPower(long power) {
        this.power = power;
        return this;
    }

    public long getHealth() {
        return health;
    }

    public ShipDTO setHealth(long health) {
        this.health = health;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public ShipDTO setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public int getCategory() {
        return category;
    }

    public ShipDTO setCategory(int category) {
        this.category = category;
        return this;
    }
}
