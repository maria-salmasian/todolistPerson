package com.org.todolist.infrastructure.repository;

import com.org.todolist.infrastructure.entity.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList, Integer> {
    List<ToDoList> findAllByStatusId(Integer status);
}
