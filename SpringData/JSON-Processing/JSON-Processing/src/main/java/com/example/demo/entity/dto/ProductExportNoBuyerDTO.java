package com.example.demo.entity.dto;

import java.math.BigDecimal;

public class ProductExportNoBuyerDTO {
    private String name;
    private BigDecimal price;
    private String seller;

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
