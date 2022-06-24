package bg.softuni.jira.model.dto;

import bg.softuni.jira.model.entity.ClassificationName;
import bg.softuni.jira.model.entity.Progress;

import java.time.LocalDate;

public class TaskDTO {
    private long id;
    private String name;
    private String userName;
    private ClassificationName classification;
    private LocalDate dueDate;
    private Progress progress;

    public long getId() {
        return id;
    }

    public TaskDTO setId(long id) {
        this.id = id;
        return this;
    }


    public String getName() {
        return name;
    }

    public TaskDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public TaskDTO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public ClassificationName getClassification() {
        return classification;
    }

    public TaskDTO setClassification(ClassificationName classification) {
        this.classification = classification;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskDTO setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public Progress getProgress() {
        return progress;
    }

    public TaskDTO setProgress(Progress progress) {
        this.progress = progress;
        return this;
    }
}
