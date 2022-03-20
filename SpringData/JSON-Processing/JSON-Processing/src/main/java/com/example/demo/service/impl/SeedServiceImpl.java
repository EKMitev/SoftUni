package com.example.demo.service.impl;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.entity.dto.CategoryImportDTO;
import com.example.demo.entity.dto.ProductImportDTO;
import com.example.demo.entity.dto.UserImportDTO;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.SeedService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;

@Service
public class SeedServiceImpl implements SeedService {

    private final Gson gson;
    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;

        this.mapper = new ModelMapper();
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void seedUsers() throws FileNotFoundException {
        FileReader reader = new FileReader(USERS_JSON_PATH);
        UserImportDTO[] dto = this.gson.fromJson(reader, UserImportDTO[].class);

        Arrays.stream(dto)
                .map(d -> mapper.map(d, User.class))
                .forEach(this.userRepository::save);
    }

    @Override
    public void seedProducts() throws FileNotFoundException {
        FileReader reader = new FileReader(PRODUCTS_JSON_PATH);
        ProductImportDTO[] dto = this.gson.fromJson(reader, ProductImportDTO[].class);

        Arrays.stream(dto)
                .map(d -> mapper.map(d, Product.class))
                .map(this::setRandomProperties)
                .forEach(this.productRepository::save);
    }

    private Product setRandomProperties(Product product) {
        Random random = new Random();

        long count = userRepository.count();

        Optional<User> seller = this.userRepository.findById(random.nextInt((int) count) + 1);
        Optional<User> buyer = this.userRepository.findById(random.nextInt((int) count) + 1);

        product.setSeller(seller.get());
        if (product.getPrice().compareTo(BigDecimal.valueOf(944)) > 0) {
            product.setBuyer(buyer.get());
        }

        long categoriesCount = this.categoryRepository.count();
        int currentCount = random.nextInt((int) categoriesCount);

        Set<Category> categories = new HashSet<>();
        for (int i = 0; i < currentCount; i++) {
            int randomId = random.nextInt((int) categoriesCount) + 1;

            Optional<Category> randomCategory = this.categoryRepository.findById(randomId);

            categories.add(randomCategory.get());
        }

        product.setCategories(categories);

        return product;
    }

    @Override
    public void seedCategories() throws FileNotFoundException {
        FileReader reader = new FileReader(CATEGORIES_JSON_PATH);
        CategoryImportDTO[] dto = this.gson.fromJson(reader, CategoryImportDTO[].class);

        Arrays.stream(dto)
                .map(d -> mapper.map(d, Category.class))
                .forEach(this.categoryRepository::save);
    }
}
