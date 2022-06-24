package bg.softuni.jira.model.validation;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String first;
    private String second;
    private String message;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.first = constraintAnnotation.first();
        this.second = constraintAnnotation.second();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = PropertyAccessorFactory
                .forBeanPropertyAccess(value);

        boolean valid;
        Object firstVal = beanWrapper.getPropertyValue(this.first);
        Object secondVal = beanWrapper.getPropertyValue(this.second);

        if (firstVal == null) {
            valid = secondVal == null;
        } else {
            valid = secondVal.equals(firstVal);
        }

        if (!valid) {
            context
                .buildConstraintViolationWithTemplate(message)
                .addPropertyNode(second)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        }

        return valid;
    }
}
