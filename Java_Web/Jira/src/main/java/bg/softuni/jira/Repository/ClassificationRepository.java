package bg.softuni.jira.Repository;

import bg.softuni.jira.model.entity.Classification;
import bg.softuni.jira.model.entity.ClassificationName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassificationRepository extends JpaRepository<Classification, Long> {
    Classification getByName(ClassificationName classificationName);
}
