package org.icadev.tasks.services.impl;

import org.icadev.tasks.domain.entities.TaskList;
import org.icadev.tasks.repositories.TaskListRepository;
import org.icadev.tasks.services.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {

        if(taskList.getId() != null){
            throw new IllegalArgumentException("Task List already has an ID!");
        }

        if(taskList.getTitle() == null || taskList.getTitle().isBlank()){
            throw new IllegalArgumentException("Task List title must be present!");
        }

        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
        ));
    }

    @Override
    public Optional<TaskList> getTaskList(UUID taskListId) {
        return taskListRepository.findById(taskListId);
    }

    @Override
    public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
        if(taskList.getId() == null){
            throw new IllegalArgumentException("Task List ID must be present!");
        }

        if(!taskListId.equals(taskList.getId())){
            throw new IllegalArgumentException("Task List ID must match the one provided!");
        }

        TaskList existingTaskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("Task List not found!"));

        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setDescription(taskList.getDescription());
        existingTaskList.setUpdated(LocalDateTime.now());
        return taskListRepository.save(existingTaskList);
    }

    @Override
    public void deleteTaskList(UUID taskListId) {
        if(!taskListRepository.existsById(taskListId)){
            throw new IllegalArgumentException("Task List not found!");
        }

        taskListRepository.deleteById(taskListId);
    }
}
