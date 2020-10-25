package com.org.todolist.core.service;


import com.org.todolist.infrastructure.entity.ToDoList;
import com.org.todolist.utils.StatusEnum;
import com.org.todolist.ws.dto.ToDoListDTO;
import com.org.todolist.ws.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ToDoListService {

    void saveToDoList(ToDoListDTO toDoList) throws NotFoundException;
    ToDoList updateToDoListByID(int id, ToDoListDTO toDoListDTO) throws NotFoundException;
    void deleteToDoListByID(int id) throws NotFoundException;
    void deleteAllToDoList() throws NotFoundException;
    ToDoListDTO getToDoListByID(int id) throws NotFoundException;
    List<ToDoListDTO> getOrderedToDoListItems() throws NotFoundException;
    List<ToDoListDTO> getToDoListItemsBasedOnStatus(StatusEnum status) throws NotFoundException;
    List<ToDoListDTO> getActiveToDoListItems() throws NotFoundException;

}
