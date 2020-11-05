package com.org.todolist.infrastructure.entity;

import com.org.todolist.utils.enumeration.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Status {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEnum status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "status")
    private List<ToDoList> toDoItems;

    public Status(StatusEnum status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}