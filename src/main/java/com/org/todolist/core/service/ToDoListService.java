package com.org.todolist.core.service;


import com.org.todolist.core.model.ToDoListModel;
import com.org.todolist.core.service.exception.ToDoListNotFoundException;
import com.org.todolist.core.service.exception.UserNotFoundException;
import com.org.todolist.core.service.exception.ValidationException;
import com.org.todolist.infrastructure.entity.ToDoList;
import com.org.todolist.utils.enumeration.StatusEnum;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ToDoListService {

    ToDoList saveToDoList(ToDoListModel toDoListModel) throws ValidationException, UserNotFoundException;
    ToDoList updateToDoListByID(int id, ToDoListModel toDoListModel) throws UserNotFoundException, ToDoListNotFoundException, ValidationException;
    void deleteToDoListByID(int id) throws  ToDoListNotFoundException;
    void deleteAllToDoList() ;
    ToDoListModel getToDoListByID(int id) throws  ToDoListNotFoundException;
    List<ToDoListModel> getOrderedToDoListItems() ;
    List<ToDoListModel> getToDoListItemsBasedOnStatus(StatusEnum status);
    List<ToDoListModel> getActiveToDoListItems();

}
