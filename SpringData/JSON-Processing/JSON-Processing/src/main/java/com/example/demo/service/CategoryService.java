package com.example.demo.service;

import com.example.demo.entity.dto.CategoryByProdCountDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryByProdCountDTO> getCategoriesStats();
}
