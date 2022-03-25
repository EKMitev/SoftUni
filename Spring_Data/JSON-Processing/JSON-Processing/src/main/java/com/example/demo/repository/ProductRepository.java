package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> getByPriceBetweenAndBuyerIsNull(BigDecimal lowerBound, BigDecimal upperBound);
}
