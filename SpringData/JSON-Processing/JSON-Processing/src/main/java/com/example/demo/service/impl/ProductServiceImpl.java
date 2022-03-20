package com.example.demo.service.impl;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.entity.dto.ProductExportNoBuyerDTO;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private final ModelMapper mapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;

        this.mapper = new ModelMapper();
    }

    @Override
    public List<ProductExportNoBuyerDTO> getByPriceInRange(float from, float to) {
        TypeMap<Product, ProductExportNoBuyerDTO> typeMap = mapper.createTypeMap(Product.class, ProductExportNoBuyerDTO.class);
        typeMap.addMappings(m -> m.map(src -> src.getSeller().getFullName(), ProductExportNoBuyerDTO::setSeller));
        typeMap.addMappings(m -> m.map(Product::getName, ProductExportNoBuyerDTO::setName));
        typeMap.addMappings(m -> m.map(Product::getPrice, ProductExportNoBuyerDTO::setPrice));

        List<Product> products = this.productRepository.getByPriceBetweenAndBuyerIsNull(BigDecimal.valueOf(from), BigDecimal.valueOf(to));

         return products.stream()
                .map(typeMap::map)
                 .collect(Collectors.toList());
    }
}
