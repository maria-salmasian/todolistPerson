package com.org.todolist.ws.dto;

import com.org.todolist.infrastructure.entity.Gender;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GenderDTO {
    private int id; //primKey
    private Gender gender;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
