package com.example.demo.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 15)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Product> products;

    public Category() {
        this.products = new HashSet<>();
    }

    public Category(String name) {
        this();
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return Collections.unmodifiableSet(products);
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public int getProductsSize() {
        return this.products.size();
    }

    public double getProductsAveragePrice() {
        OptionalDouble average = this.products.stream().mapToDouble(p -> p.getPrice().doubleValue()).average();
        if (average.isPresent()){
            return average.getAsDouble();
        }
        return -1;
    }

    public double getProductsTotalRevenue() {
        return this.products.stream().mapToDouble(p -> p.getPrice().doubleValue()).sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
