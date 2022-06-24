package bg.softuni.jira.model.dto;

import bg.softuni.jira.model.entity.ClassificationName;
import bg.softuni.jira.model.validation.NameNotUsed;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class TaskAddDTO {

    @Size(min = 3, max = 20, message = "Name length must be between 3 and 20 characters.")
    @NameNotUsed(message = "Task already created.")
    private String name;

    @Size(min = 5)
    private String description;

    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate dueDate;

    @NotNull
    private ClassificationName classification;

    public String getName() {
        return name;
    }

    public TaskAddDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TaskAddDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskAddDTO setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public ClassificationName getClassification() {
        return classification;
    }

    public TaskAddDTO setClassification(ClassificationName classification) {
        this.classification = classification;
        return this;
    }
}
