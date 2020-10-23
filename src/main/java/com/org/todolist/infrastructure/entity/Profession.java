package com.org.todolist.infrastructure.entity;

import com.org.todolist.utils.ProfessionEnum;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
public class Profession {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id; //primKey

    @Column(name = "profession")
     private ProfessionEnum profession;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "isDeleted_flg")
    private  boolean isDeleted = false;






}
