package org.icadev.tasks.domain.dto;

import org.icadev.tasks.domain.entities.TaskPriority;
import org.icadev.tasks.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status
) {

}
