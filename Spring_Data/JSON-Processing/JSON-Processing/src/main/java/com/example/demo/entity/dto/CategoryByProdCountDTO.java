package com.example.demo.entity.dto;

import java.math.BigDecimal;

public class CategoryByProdCountDTO {
    private String category;
    private int productsCount;
    private BigDecimal averagePrice;
    private BigDecimal totalRevenue;

    public int getProductsCount() {
        return productsCount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setProductsCount(int productsCount) {
        this.productsCount = productsCount;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
