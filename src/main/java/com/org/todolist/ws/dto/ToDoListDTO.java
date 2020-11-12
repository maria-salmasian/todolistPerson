package com.org.todolist.ws.dto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ToDoListDTO  {
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
