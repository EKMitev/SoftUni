package com.example.demo.service.impl;

import com.example.demo.entity.Category;
import com.example.demo.entity.dto.CategoryByProdCountDTO;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;

        this.mapper = new ModelMapper();
        TypeMap<Category, CategoryByProdCountDTO> typeMap = this.mapper.createTypeMap(Category.class, CategoryByProdCountDTO.class);
        typeMap.addMappings(m -> m.map(Category::getName, CategoryByProdCountDTO::setCategory))
                .addMappings(m -> m.map(Category::getProductsSize, CategoryByProdCountDTO::setProductsCount))
                .addMappings(m -> m.map(Category::getProductsAveragePrice, CategoryByProdCountDTO::setAveragePrice))
                .addMappings(m -> m.map(Category::getProductsTotalRevenue, CategoryByProdCountDTO::setTotalRevenue));
    }

    @Override
    @Transactional
    public List<CategoryByProdCountDTO> getCategoriesStats() {
        List<Category> all = this.categoryRepository.findAll();

        return all
                .stream()
                .map(c -> this.mapper.map(c, CategoryByProdCountDTO.class))
                .sorted((f, s) -> s.getProductsCount() - f.getProductsCount())
                .collect(Collectors.toList());
    }
}
