package org.icadev.tasks.services;

import org.icadev.tasks.domain.entities.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    List<Task> listTasks(UUID taskListId);
    Task createTask(UUID taskListId, Task task);
}
