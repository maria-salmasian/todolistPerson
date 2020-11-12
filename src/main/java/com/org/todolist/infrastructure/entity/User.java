package com.org.todolist.infrastructure.entity;


import com.org.todolist.utils.enumeration.GenderEnum;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;


import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@Table(name = "users", uniqueConstraints=
@UniqueConstraint(columnNames={"passport_no"}))
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "passport_no", unique = true)
    private long passportNo;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "surname")
    private String surname;

    @Email(message = "email not valid")
    @Column(name = "email")
    private String email;

    @Range(min=20, max=1000, message = "salary out of range")
    @Column(name = "salary")
    private long salary;

    @Range(min=18, max=45, message = "age out of range")
    @Column(name = "age")
    private int age;

    @ManyToOne()
    private Gender gender;

    @ManyToOne()
    private Profession profession;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ToDoList> toDoItems;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_deleted")
    private boolean isDeleted;

}