package com.example.demo.service;

import com.example.demo.models.mapper.ModelMapper;
import com.example.demo.models.dto.BrandDTO;
import com.example.demo.models.dto.ModelDTO;
import com.example.demo.models.entity.Brand;
import com.example.demo.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    public BrandService(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    public List<BrandDTO> getAllBrands() {
        return this.brandRepository
                .findAll()
                .stream()
                .map(this::mapBrand)
                .toList();
    }

    private BrandDTO mapBrand(Brand brandEntity) {
        List<ModelDTO> models = brandEntity
                .getModels()
                .stream()
                .map(this.modelMapper::mapEntityToModelDto)
                .toList();

        return new BrandDTO()
                .setModels(models)
                .setName(brandEntity.getName());
    }
}
