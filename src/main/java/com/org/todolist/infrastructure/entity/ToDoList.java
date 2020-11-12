package com.org.todolist.infrastructure.entity;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class ToDoList {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne
    private Status status;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @NotNull
    @Column(name = "description")
    private String description;


    @NotNull
    @ManyToOne()
    private User user;
}