package com.org.todolist.ws.dto;

import com.org.todolist.infrastructure.entity.Gender;
import com.org.todolist.utils.enumeration.GenderEnum;
import com.org.todolist.utils.enumeration.ProfessionEnum;
import com.sun.istack.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDTO {

    @NotNull
    private String name;
    private String surname;
    private long salary;
    private int age;
    private int gender;
    private int profession;
    private long passportNo;
    private List<ToDoListDTO> toDoItems;
//    private boolean isDeleted;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
}

