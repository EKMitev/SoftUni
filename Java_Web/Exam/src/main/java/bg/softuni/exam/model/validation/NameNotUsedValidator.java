package bg.softuni.exam.model.validation;

import bg.softuni.exam.repository.SongRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameNotUsedValidator implements ConstraintValidator<NameNotUsed, String> {
    private final SongRepository songRepository;

    public NameNotUsedValidator(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public void initialize(NameNotUsed constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.songRepository.findByPerformer(value)
                .isEmpty();
    }
}
