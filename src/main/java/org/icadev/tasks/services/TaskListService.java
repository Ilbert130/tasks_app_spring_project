package org.icadev.tasks.services;

import org.icadev.tasks.domain.entities.TaskList;

import java.util.List;

public interface TaskListService {

    List<TaskList> listTaskLists();
}
