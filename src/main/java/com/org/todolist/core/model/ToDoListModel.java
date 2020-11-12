package com.org.todolist.core.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoListModel {
    private Integer id;
    @NotNull
    private int userId;
    @NotNull
    private int statusId;
    private boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @NotNull
    private String description;


}
