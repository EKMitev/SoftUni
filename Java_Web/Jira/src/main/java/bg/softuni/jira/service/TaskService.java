package bg.softuni.jira.service;

import bg.softuni.jira.Repository.TaskRepository;
import bg.softuni.jira.model.dto.TaskAddDTO;
import bg.softuni.jira.model.dto.TaskDTO;
import bg.softuni.jira.model.entity.Progress;
import bg.softuni.jira.model.entity.Task;
import bg.softuni.jira.model.entity.User;
import bg.softuni.jira.model.mapper.TaskMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskMapper taskMapper;
    private final AuthService authService;
    private final TaskRepository taskRepository;

    public TaskService(TaskMapper taskMapper, AuthService authService, TaskRepository taskRepository) {
        this.taskMapper = taskMapper;
        this.authService = authService;
        this.taskRepository = taskRepository;
    }

    public void addTask(TaskAddDTO taskAddDTO) {
        User user = this.authService.getLoggedUser();
        Task newTask = this.taskMapper.mapFromAddDto(taskAddDTO)
                .setProgress(Progress.OPEN)
                .setUser(user);

        this.taskRepository.save(newTask);
    }

    public List<TaskDTO> getAllTasks() {
        return this.taskRepository.findAll()
                .stream()
                .map(this.taskMapper::mapFromEntity)
                .toList();
    }

    public void progress(long id) {
        Optional<Task> optionalTask = this.taskRepository.findById(id);

        if(optionalTask.isEmpty()) {
            return;
        }

        Task task = optionalTask.get();
        Progress progress = task.getProgress();

        switch (progress) {
            case OPEN -> this.taskRepository.save(task.setProgress(Progress.IN_PROGRESS));
            case IN_PROGRESS -> this.taskRepository.save(task.setProgress(Progress.COMPLETED));
            case COMPLETED -> this.taskRepository.delete(task);
        }


    }
}
