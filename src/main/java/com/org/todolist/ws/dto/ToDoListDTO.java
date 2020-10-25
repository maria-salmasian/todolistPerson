package com.org.todolist.ws.dto;

import com.org.todolist.utils.StatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ToDoListDTO  {
    private int userId;
    private StatusEnum status;
    private boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String description;

}
