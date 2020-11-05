package com.org.todolist.ws.dto;

import com.org.todolist.infrastructure.entity.Gender;
import com.org.todolist.utils.enumeration.GenderEnum;
import com.org.todolist.utils.enumeration.ProfessionEnum;
import com.sun.istack.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDTO {

   private Integer id;
    private String name;
    private String surname;
    private long salary;
    private String email;
    private int age;
    private int genderId;
    private int professionId;
    private long passportNo;
    private List<ToDoListDTO> toDoItems;

}

