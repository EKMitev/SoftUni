package bg.softuni.coffeeshop.model.dto;

import bg.softuni.coffeeshop.model.entity.Category;
import bg.softuni.coffeeshop.model.entity.OrderType;

import java.math.BigDecimal;

public class OrderDTO {
    private long id;
    private String name;
    private BigDecimal price;

    private Category category;

    public Category getCategory() {
        return category;
    }

    public OrderDTO setCategory(Category category) {
        this.category = category;
        return this;
    }

    public long getId() {
        return id;
    }

    public OrderDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderDTO setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
