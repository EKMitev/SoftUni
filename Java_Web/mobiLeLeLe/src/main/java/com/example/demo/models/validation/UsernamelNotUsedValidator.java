package com.example.demo.models.validation;

import com.example.demo.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernamelNotUsedValidator implements ConstraintValidator<UsernameNotUsed, String> {

    private final UserRepository userRepository;

    public UsernamelNotUsedValidator(UserRepository userRepository) {
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
