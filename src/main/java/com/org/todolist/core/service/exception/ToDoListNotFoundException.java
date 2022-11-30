package com.org.todolist.core.service.exception;

public class ToDoListNotFoundException extends NotFoundException {
    public ToDoListNotFoundException(String message) {
        super(message);
    }
}
