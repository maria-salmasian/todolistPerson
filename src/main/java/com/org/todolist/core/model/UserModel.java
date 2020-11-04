package com.org.todolist.core.model;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserModel {
    private String name;
    private String surname;
    private long salary;
    private int age;
    private int gender;
    private int profession;
    private long passportNo;
    private boolean isDeleted;
    private List<ToDoListModel> toDoItems;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



}
