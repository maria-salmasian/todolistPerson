package com.org.todolist;


import com.org.todolist.core.service.impl.UserServiceImpl;
import com.org.todolist.infrastructure.entity.User;
import com.org.todolist.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UserServiceTest extends TodolistApplicationTests {

    @Mock
    UserRepository userRepository ;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @BeforeAll
    public static void setUp() {


    }


    @Test
    public void getUsersEmptyException(){
        Mockito.when(userRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertThrows(IllegalArgumentException.class, ()-> userServiceImpl.getUsers());
    }

    @Test
    public void getUsersOk(){
        //User x = new ....
        //es tal argument
        Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(new User(), new User()));

       List user =  userServiceImpl.getUsers();
       //check user lenght
        org.assertj.core.api.Assertions.assertThat(user.size()).isEqualTo(2);

    }

}