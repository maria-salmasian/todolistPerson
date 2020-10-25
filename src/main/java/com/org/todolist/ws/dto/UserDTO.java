package com.org.todolist.ws.dto;

import com.org.todolist.utils.GenderEnum;
import com.org.todolist.utils.ProfessionEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {

    private String name;
    private String surname;
    private long salary;
    private int age;
    private GenderEnum gender;
    private ProfessionEnum profession;
    private boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
