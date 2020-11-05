package com.org.todolist.core.model;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserModel {
    private Integer id;
    private String name;
    private String surname;
    private long salary;
    private String email;
    private int age;
    private int genderId;
    private int professionId;
    private long passportNo;
    private boolean isDeleted;
    private List<ToDoListModel> toDoItems;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



}
