package bg.softuni.coffeeshop.seed;

import bg.softuni.coffeeshop.model.entity.Category;
import bg.softuni.coffeeshop.model.entity.OrderType;
import bg.softuni.coffeeshop.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Seeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public Seeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedCategory();
    }

    private void seedCategory() {
        if (this.categoryRepository.count() == 0) {
            List<Category> categories =
                    Arrays.stream(OrderType.values())
                            .map(Category::new).toList();

            this.categoryRepository.saveAll(categories);
        }
    }
}
