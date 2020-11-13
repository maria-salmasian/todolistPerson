package com.org.todolist.ws.dto;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Email;
import java.math.BigDecimal;
import java.util.List;


@Data
@NoArgsConstructor
public class UserDTO{
     private Integer id;
     @NotNull
     private String name;
     @NotNull
     private String surname;
     @Range(min = 20, max = 1000, message = "salary out of range")
     private BigDecimal salary;
     @Email(message = "email not valid")
     private String email;
     @Range(min = 18, max = 45, message = "age out of range")
     private int age;
     @Range(min = 1, max = 2, message = "gender not found")
     private int genderId;
     @Range(min = 1, max = 3, message = "profession not found")
     private int professionId;
     private long passportNo;
     private List<ToDoListDTO> toDoItems;
}

