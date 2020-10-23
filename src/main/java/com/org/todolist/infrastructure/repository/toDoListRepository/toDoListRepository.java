package com.org.todolist.infrastructure.repository.toDoListRepository;

import com.org.todolist.infrastructure.entity.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface toDoListRepository  extends JpaRepository<ToDoList, Integer> {
}
