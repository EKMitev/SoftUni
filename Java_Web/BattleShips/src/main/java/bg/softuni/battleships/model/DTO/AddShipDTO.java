package bg.softuni.battleships.model.DTO;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class AddShipDTO {

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

    public AddShipDTO setName(String name) {
        this.name = name;
        return this;
    }

    public long getPower() {
        return power;
    }

    public AddShipDTO setPower(long power) {
        this.power = power;
        return this;
    }

    public long getHealth() {
        return health;
    }

    public AddShipDTO setHealth(long health) {
        this.health = health;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public AddShipDTO setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public int getCategory() {
        return category;
    }

    public AddShipDTO setCategory(int category) {
        this.category = category;
        return this;
    }
}
