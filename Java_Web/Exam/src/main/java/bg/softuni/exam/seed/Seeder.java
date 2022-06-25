package bg.softuni.exam.seed;

import bg.softuni.exam.model.entity.Style;
import bg.softuni.exam.model.entity.StyleName;
import bg.softuni.exam.repository.StyleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Seeder implements CommandLineRunner {

    private final StyleRepository styleRepository;

    public Seeder(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.styleRepository.count() == 0) {
            List<Style> categories =
                    Arrays.stream(StyleName.values())
                            .map(Style::new).toList();

            this.styleRepository.saveAll(categories);
        }
    }
}
