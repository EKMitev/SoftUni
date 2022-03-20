package com.example.demo;

import com.example.demo.entity.dto.CategoryByProdCountDTO;
import com.example.demo.entity.dto.ProductExportNoBuyerDTO;
import com.example.demo.entity.dto.UsersWithBuyerDTO;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import com.example.demo.service.SeedService;
import com.example.demo.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final SeedService seedService;
    private final ProductService productService;
    private final CategoryService categoryService;

    private final Gson gson;
    private final UserService userService;

    @Autowired
    public ConsoleRunner(SeedService seedService, ProductService productService, CategoryService categoryService, UserService userService) {
        this.seedService = seedService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.userService = userService;


        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void run(String... args) throws Exception {
        //seedService.seedAll();

        //System.out.println(getProductsForSaleInRange(500, 1000));

        //System.out.println(getUserWithSoldProducts());

        System.out.println(getCategoryStats());
    }

    private String getCategoryStats() {
        List<CategoryByProdCountDTO> categoriesStats = this.categoryService.getCategoriesStats();
        return this.gson.toJson(categoriesStats);
    }

    private String getUserWithSoldProducts() {
        List<UsersWithBuyerDTO> usersWithSoldProducts = this.userService.findUsersWithSoldProducts();
        return gson.toJson(usersWithSoldProducts);
    }

    private String getProductsForSaleInRange(float from, float to) {
        List<ProductExportNoBuyerDTO> prods = productService.getByPriceInRange(from, to);
        return gson.toJson(prods);
    }
}
