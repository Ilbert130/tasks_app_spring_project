package org.icadev.tasks.controllers;

import org.icadev.tasks.domain.dto.TaskListDto;
import org.icadev.tasks.mappers.TaskListMapper;
import org.icadev.tasks.services.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="/api/task-lists" )
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDto> listTaskLists() {
        return taskListService.listTaskLists()
                .stream()
                .map(taskListMapper::toDto)
                .toList();
    }

    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
        return taskListMapper.toDto(taskListService.createTaskList(taskListMapper.fromDto(taskListDto)));
    }
}
