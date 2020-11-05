package com.org.todolist.core.model;

import com.org.todolist.utils.enumeration.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoListModel {
    private Integer id;
    private int userId;
    private int statusId;
    private boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String description;


}
