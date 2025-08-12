package org.icadev.tasks.mappers;

import org.icadev.tasks.domain.dto.TaskDto;
import org.icadev.tasks.domain.entities.Task;

public interface TaskMapper {
    Task fromDto(TaskDto taskDto);
    TaskDto toDto(Task task);
}
