package org.icadev.tasks.services.impl;

import org.icadev.tasks.domain.entities.Task;
import org.icadev.tasks.domain.entities.TaskList;
import org.icadev.tasks.domain.entities.TaskPriority;
import org.icadev.tasks.domain.entities.TaskStatus;
import org.icadev.tasks.repositories.TaskListRepository;
import org.icadev.tasks.repositories.TaskRepository;
import org.icadev.tasks.services.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    public Task createTask(UUID taskListId, Task task) {

        if(task.getId() != null){
            throw new IllegalArgumentException("Task already has an ID!");
        }

        if(task.getTitle() == null || task.getTitle().isBlank()){
            throw new IllegalArgumentException("Task title must be present!");
        }

        TaskPriority taskPriority = Optional.ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM);
        TaskStatus taskStatus = TaskStatus.OPEN;

        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("Task List not found!"));

        LocalDateTime now = LocalDateTime.now();

        Task taskSaved = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskPriority,
                taskList,
                now,
                now
        );

        return taskRepository.save(taskSaved);
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId, taskId).or(() -> {
            throw new IllegalArgumentException("Task not found!");
        });
    }
}
