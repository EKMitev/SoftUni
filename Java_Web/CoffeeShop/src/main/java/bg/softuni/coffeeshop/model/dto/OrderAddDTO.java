package bg.softuni.coffeeshop.model.dto;

import bg.softuni.coffeeshop.model.entity.OrderType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderAddDTO {

    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @Positive
    @NotNull
    private BigDecimal price;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull
    private LocalDateTime orderTime;

    @NotNull
    private OrderType category;

    @NotBlank
    @Size(min = 5)
    private String description;

    public String getName() {
        return name;
    }

    public OrderAddDTO setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderAddDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public OrderAddDTO setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public OrderType getCategory() {
        return category;
    }

    public OrderAddDTO setCategory(OrderType category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderAddDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
