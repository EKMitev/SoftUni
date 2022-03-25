package com.example.demo.entity.dto;

import java.util.List;

public class UsersWithBuyerDTO {
    private String firstName;
    private String lastName;
    private List<SoldProductDTO> soldProducts;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSoldProducts(List<SoldProductDTO> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
