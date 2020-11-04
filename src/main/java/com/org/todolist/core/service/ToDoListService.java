package com.org.todolist.core.service;


import com.org.todolist.core.model.ToDoListModel;
import com.org.todolist.infrastructure.entity.ToDoList;
import com.org.todolist.ws.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ToDoListService {

    ToDoList saveToDoList(ToDoListModel toDoListModel) throws NotFoundException;
    ToDoList updateToDoListByID(int id, ToDoListModel toDoListModel) throws NotFoundException;
    void deleteToDoListByID(int id) throws NotFoundException;
    void deleteAllToDoList() throws NotFoundException;
    ToDoListModel getToDoListByID(int id) throws NotFoundException;
    List<ToDoListModel> getOrderedToDoListItems() throws NotFoundException;
    List<ToDoListModel> getToDoListItemsBasedOnStatus(int status) throws NotFoundException;
    List<ToDoListModel> getActiveToDoListItems() throws NotFoundException;

}
