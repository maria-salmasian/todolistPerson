package com.org.todolist.infrastructure.entity;

import com.org.todolist.utils.GenderEnum;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;



@Entity
@Data
public class Gender {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id; //primKey

    @Column(name = "gender")
    private GenderEnum gender;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "gender", cascade = CascadeType.ALL)
    private User user;
    //list table


}
