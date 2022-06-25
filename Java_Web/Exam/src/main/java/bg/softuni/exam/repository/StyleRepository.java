package bg.softuni.exam.repository;

import bg.softuni.exam.model.entity.Style;
import bg.softuni.exam.model.entity.StyleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long> {

    Style getByStyleName(StyleName styleName);

}
