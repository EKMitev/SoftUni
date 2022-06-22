package bg.softuni.coffeeshop.model.validation;


import bg.softuni.coffeeshop.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameNotUsedValidator implements ConstraintValidator<UsernameNotUsed, String> {

    private final UserRepository userRepository;

    public UsernameNotUsedValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UsernameNotUsed constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.userRepository.findByUsername(value)
                .isEmpty();
    }
}
