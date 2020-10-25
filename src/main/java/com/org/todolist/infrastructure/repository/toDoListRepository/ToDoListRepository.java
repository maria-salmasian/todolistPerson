package com.org.todolist.infrastructure.repository.toDoListRepository;

import com.org.todolist.infrastructure.entity.ToDoList;
import com.org.todolist.utils.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList, Integer> {

    List<ToDoList> findAllByStatus(StatusEnum statusEnum);
}
