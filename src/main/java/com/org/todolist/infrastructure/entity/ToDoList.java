package com.org.todolist.infrastructure.entity;

import com.org.todolist.utils.StatusEnum;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class ToDoList {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id; //primKey

    @Column(name = "userId")
    private int userId; //foreignKey

    @Column(name = "status")
    private StatusEnum status;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "description")
    private String description;
}

