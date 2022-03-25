package com.example.demo.entity.dto;

import java.math.BigDecimal;

public class SoldProductDTO {
    private String name;
    private BigDecimal price;
    private String buyerFirstName;
    private String buyerLastName;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setBuyerFirstName(String buyerFirstName) {
        this.buyerFirstName = buyerFirstName;
    }

    public void setBuyerLastName(String buyerLastName) {
        this.buyerLastName = buyerLastName;
    }
}
