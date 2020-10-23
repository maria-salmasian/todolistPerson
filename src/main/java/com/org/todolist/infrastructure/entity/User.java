package com.org.todolist.infrastructure.entity;

//Setup a postgressql and add user table , user
// should have id(pk), first name , last name , salary , age , gender_id  (add 10 users) , profession_id, is_deleted ,
// created_at , updated_at


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id; //primKey

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "salary")
    private long salary;

    @Column(name = "age")
    private int age;

    @Column(name = "gender_Id")
    private int gender_d;

    @Column(name = "profession_Id")
    private int professionId;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;




}
