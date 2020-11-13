package com.org.todolist.ws.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ToDoListDTO  {
    private Integer id;
    @NotNull
    private int userId;
    @NotNull
    @Range(min = 1, max = 5, message = "status does not exist")
    private int statusId;
    private boolean isDeleted;
    @NotNull
    private String description;

}
