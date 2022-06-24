package bg.softuni.jira.model.validation;

import bg.softuni.jira.Repository.TaskRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameNotUsedValidator implements ConstraintValidator<NameNotUsed, String> {
    private TaskRepository taskRepository;

    public NameNotUsedValidator(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void initialize(NameNotUsed constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.taskRepository.findByName(value)
                .isEmpty();
    }
}
