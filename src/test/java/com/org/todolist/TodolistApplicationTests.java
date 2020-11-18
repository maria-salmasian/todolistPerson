package com.org.todolist;

import com.org.todolist.infrastructure.entity.*;
import com.org.todolist.utils.enumeration.GenderEnum;
import com.org.todolist.utils.enumeration.ProfessionEnum;
import com.org.todolist.utils.enumeration.StatusEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class TodolistApplicationTests {

    @Test
    void contextLoads() {

    }

    public User createUser() {
        User user = new User();
        user.setId(0);
        user.setPassportNo(0L);
        user.setName("name");
        user.setSurname("surname");
        user.setEmail("email");
        user.setSalary(0L);
        user.setAge(0);
        Gender gender = new Gender();
        gender.setId(0);
        gender.setGender(GenderEnum.FEMALE);
        gender.setUsers(Arrays.asList(new User()));
        user.setGender(gender);
        Profession profession = new Profession();
        profession.setId(0);
        profession.setUsers(Arrays.asList(new User()));
        user.setProfession(profession);
        ToDoList toDoList = new ToDoList();
        toDoList.setId(0);
        Status status = new Status();
        status.setId(0);
        status.setStatus(StatusEnum.IN_PROGRESS);
        status.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList.setStatus(status);
        toDoList.setDescription("description");
        toDoList.setUser(new User());
        user.setToDoItems(Arrays.asList(toDoList));
        return user;

    }


    public ToDoList createToDoList(){
        ToDoList toDoList = new ToDoList();
        toDoList.setId(0);
        Status status = new Status();
        status.setId(0);
        status.setStatus(StatusEnum.IN_PROGRESS);
        status.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList.setStatus(status);
        toDoList.setDeleted(false);
        toDoList.setDescription("description");
        User user = createUser();
        toDoList.setUser(user);
        return toDoList;
    }

}
