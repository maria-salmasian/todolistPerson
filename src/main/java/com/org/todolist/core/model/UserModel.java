package com.org.todolist.core.model;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

//asuma petqa default unena, isk default menak chi toxum inteij dra hamar all arg ela petq
public class UserModel {
    private Integer id;
    private String name;
    private String surname;
    private BigDecimal salary;
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
