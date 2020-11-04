package com.org.todolist.infrastructure.entity;

import com.org.todolist.utils.enumeration.GenderEnum;
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
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //primKey

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GenderEnum gender;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "gender")
    private List<User> users;

    public Gender(GenderEnum gender, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.gender = gender;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}