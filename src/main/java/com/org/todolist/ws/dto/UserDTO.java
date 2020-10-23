package com.org.todolist.ws.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {
    private int id; //primKey
    private String name;
    private String surname;
    private long salary;
    private int age;
    private int gender_d;
    private int professionId;
    private boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
