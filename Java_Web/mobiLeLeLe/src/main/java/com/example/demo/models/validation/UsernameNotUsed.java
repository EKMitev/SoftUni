package com.example.demo.models.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UsernamelNotUsedValidator.class)
public @interface UsernameNotUsed {

    String message() default "Already Used";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
