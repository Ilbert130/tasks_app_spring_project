package org.icadev.tasks.mappers;

import org.icadev.tasks.domain.dto.TaskListDto;
import org.icadev.tasks.domain.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto taskListDto);
    TaskListDto toDto(TaskList taskList);
}
