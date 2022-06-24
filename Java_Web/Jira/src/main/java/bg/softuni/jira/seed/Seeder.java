package bg.softuni.jira.seed;

import bg.softuni.jira.Repository.ClassificationRepository;
import bg.softuni.jira.model.entity.Classification;
import bg.softuni.jira.model.entity.ClassificationName;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Seeder implements CommandLineRunner {

    private final ClassificationRepository classificationRepository;

    public Seeder(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedCategory();
    }

    private void seedCategory() {
        if (this.classificationRepository.count() == 0) {
            List<Classification> categories =
                    Arrays.stream(ClassificationName.values())
                            .map(Classification::new).toList();

            this.classificationRepository.saveAll(categories);
        }
    }
}
