package com.example.demo.service;

import java.io.FileNotFoundException;

public interface SeedService {
    String PATH = "JSON-Processing/src/main/resources/files";

    String CATEGORIES_JSON_PATH = PATH + "/categories.json";
    String PRODUCTS_JSON_PATH = PATH + "/products.json";
    String USERS_JSON_PATH = PATH + "/users.json";


    void seedUsers() throws FileNotFoundException;

    void seedProducts() throws FileNotFoundException;

    void seedCategories() throws FileNotFoundException;

    default void seedAll() throws FileNotFoundException {
        seedUsers();
        seedCategories();
        seedProducts();
    }
}
