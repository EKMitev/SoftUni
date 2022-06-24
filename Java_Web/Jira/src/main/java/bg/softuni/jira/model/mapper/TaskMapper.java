package bg.softuni.jira.model.mapper;

import bg.softuni.jira.Repository.ClassificationRepository;
import bg.softuni.jira.model.dto.TaskAddDTO;
import bg.softuni.jira.model.dto.TaskDTO;
import bg.softuni.jira.model.entity.Classification;
import bg.softuni.jira.model.entity.Task;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class TaskMapper {

    @Autowired
    private ClassificationRepository classificationRepository;

    public Task mapFromAddDto(TaskAddDTO taskAddDTO) {
        if (taskAddDTO == null) {
            return null;
        }

        Classification classification = classificationRepository.getByName(taskAddDTO.getClassification());

        Task task = new Task();

      return   task
                .setName(taskAddDTO.getName())
                .setDueDate(taskAddDTO.getDueDate())
                .setClassification(classification);
    }

    public TaskDTO mapFromEntity(Task task) {
        return new TaskDTO()
                .setId(task.getId())
                .setName(task.getName())
                .setUserName(task.getUser().getUsername())
                .setClassification(task.getClassification().getName())
                .setDueDate(task.getDueDate())
                .setProgress(task.getProgress());
    }
}
