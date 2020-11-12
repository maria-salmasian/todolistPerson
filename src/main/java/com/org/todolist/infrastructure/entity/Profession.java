package com.org.todolist.infrastructure.entity;

import com.org.todolist.utils.enumeration.ProfessionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class Profession {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "profession")
    private ProfessionEnum profession;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "isDeleted_flg")
    private  boolean isDeleted = false;

    @OneToMany(mappedBy = "profession", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users;

}