package com.example.demo.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    private int age;

    @OneToMany(mappedBy = "buyer")
    private List<Product> soldProducts;

    @OneToMany(mappedBy = "seller")
    private List<Product> boughtProducts;

    @ManyToMany
    private Set<User> friends;

    public User() {
        this.boughtProducts = new ArrayList<>();
        this.soldProducts = new ArrayList<>();
        this.friends = new HashSet<>();
    }

    public User(String firstName, String lastName, int age) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        if (this.firstName == null){
            return this.lastName;
        }
        return this.firstName + " " + this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Product> getSoldProducts() {
        return Collections.unmodifiableList(soldProducts);
    }

    public void setSoldProducts(List<Product> soldProducts) {
        this.soldProducts = soldProducts;
    }

    public List<Product> getBoughtProducts() {
        return Collections.unmodifiableList(boughtProducts);
    }

    public void setBoughtProducts(List<Product> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    public Set<User> getFriends() {
        return Collections.unmodifiableSet(friends);
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }
}
