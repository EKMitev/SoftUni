package bg.softuni.coffeeshop.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderType name;

    private int neededTime;

    public Category() {}

    public Category(OrderType name) {
        this.name = name;
        setTime();
    }

    public long getId() {
        return id;
    }

    public Category setId(long id) {
        this.id = id;
        return this;
    }

    public OrderType getName() {
        return name;
    }

    public Category setName(OrderType name) {
        this.name = name;
        return this;
    }

    public int getNeededTime() {
        return neededTime;
    }

    public Category setNeededTime(int neededTime) {
        this.neededTime = neededTime;
        return this;
    }

    private void setTime() {
        switch (this.name){
            case DRINK -> setNeededTime(1);
            case COFFEE -> setNeededTime(2);
            case OTHER -> setNeededTime(5);
            case CAKE -> setNeededTime(10);
            default -> throw new IllegalStateException("Unexpected value: " + this.name);
        }

    }
}
