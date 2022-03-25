package com.example.demo.service;

import com.example.demo.entity.dto.ProductExportNoBuyerDTO;

import java.util.List;

public interface ProductService {
    List<ProductExportNoBuyerDTO> getByPriceInRange(float from, float to);
}
