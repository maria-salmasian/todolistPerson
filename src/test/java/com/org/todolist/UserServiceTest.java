package com.org.todolist;

import com.org.todolist.core.model.ToDoListModel;
import com.org.todolist.core.model.UserModel;
import com.org.todolist.core.service.exception.UserNotFoundException;
import com.org.todolist.core.service.exception.ValidationException;
import com.org.todolist.core.service.impl.UserServiceImpl;
import com.org.todolist.infrastructure.entity.User;
import com.org.todolist.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

public class UserServiceTest extends TodolistApplicationTests {

    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetUsersOK() {
        List<UserModel> expected = Collections.singletonList(new UserModel(0, "name", "surname", new BigDecimal("0.00"), "email", 0, 0, 0, 0L, false, Arrays.asList(new ToDoListModel(0, 0, 0, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description")), LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
        User user = createUser();
        List<User> userList = Collections.singletonList(user);
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        UserModel userModel = new UserModel(0, "name", "surname", new BigDecimal("0.00"), "email", 0, 0, 0, 0L, false, Arrays.asList(new ToDoListModel(0, 0, 0, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description")), LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        Mockito.when(modelMapper.map(user, UserModel.class)).thenReturn(userModel);
        List<UserModel> result = userServiceImpl.getUsers();

        assertEquals(expected, result);
    }

    @Test
    void getUserByIDOK() throws Exception {
        UserModel expected = new UserModel(0, "name", "surname", new BigDecimal("0.00"), "email", 0, 0, 0, 0L, false, Arrays.asList(new ToDoListModel(0, 0, 0, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description")), LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        User user1 = createUser();
        Optional<User> user = Optional.of(user1);
        Mockito.when(userRepository.findById(0)).thenReturn(user);
        UserModel userModel = new UserModel(0, "name", "surname", new BigDecimal("0.00"), "email", 0, 0, 0, 0L, false, Arrays.asList(new ToDoListModel(0, 0, 0, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description")), LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        Mockito.when(modelMapper.map(user1, UserModel.class)).thenReturn(userModel);
        UserModel result = userServiceImpl.getUserByID(0);

        assertEquals(expected, result);
    }

    @Test
    void getUserByIDThrowsUserNotFoundException() {
        Optional<User> user = Optional.empty();
        Mockito.when(userRepository.findById(0)).thenReturn(user);
        UserModel userModel = new UserModel(0, "name", "surname", new BigDecimal("0.00"), "email", 0, 0, 0, 0L, false, Arrays.asList(new ToDoListModel(0, 0, 0, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description")), LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        Mockito.when(modelMapper.map(new User(), UserModel.class)).thenReturn(userModel);

        assertThrows(UserNotFoundException.class, () -> userServiceImpl.getUserByID(0));
    }

    @Test
    void saveUserOk() throws Exception {
        UserModel userModel = new UserModel(0, "name", "surname", new BigDecimal("0.00"), "email", 0, 0, 0, 0L, false, Arrays.asList(new ToDoListModel(0, 0, 0, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description")), LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        User expected = createUser();
        User user = createUser();
        Mockito.when(modelMapper.map(userModel, User.class)).thenReturn(user);
        User user1 = createUser();
        Mockito.when(userRepository.save(user)).thenReturn(user1);
        User result = userServiceImpl.saveUser(userModel);

        assertEquals(expected, result);
    }

    @Test
    void saveUserThrowsValidationException() {
        UserModel userModel = null;
        User user = new User();
        Mockito.when(modelMapper.map(userModel, User.class)).thenReturn(user);
        User user1 = new User();
        Mockito.when(userRepository.save(user)).thenReturn(user1);

        assertThrows(ValidationException.class, () -> userServiceImpl.saveUser(userModel));
    }

    @Test
    void updateUserByIDOk() throws Exception {
        UserModel userModel = new UserModel(0, "name", "surname", new BigDecimal("0.00"), "email", 0, 0, 0, 0L, false, Arrays.asList(new ToDoListModel(0, 0, 0, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description")), LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        User expected = createUser();
        User user1 = createUser();
        Optional<User> user = Optional.of(user1);
        Mockito.when(userRepository.findById(0)).thenReturn(user);
        User user2 = createUser();
        Mockito.when(modelMapper.map(userModel, User.class)).thenReturn(user2);
        User user3 = createUser();
        Mockito.when(userRepository.save(user2)).thenReturn(user3);
        User result = userServiceImpl.updateUserByID(0, userModel);

        assertEquals(expected, result);
        verify(userRepository).delete(user1);
    }


    @Test
    void deleteUserByIDOK() throws Exception {
        User user1 = createUser();
        Optional<User> user = Optional.of(user1);
        Mockito.when(userRepository.findById(0)).thenReturn(user);
        User user2 = createUser();
        Mockito.when(userRepository.save(user1)).thenReturn(user2);

        userServiceImpl.deleteUserByID(0);

    }

    @Test
    void testDeleteUserByID_ThrowsUserNotFoundException() {
        User user = new User();
        Optional<User> user1 = Optional.empty();
        Mockito.when(userRepository.findById(0)).thenReturn(user1);
        User user2 = new User();
        Mockito.when(userRepository.save(user)).thenReturn(user2);

        assertThrows(UserNotFoundException.class, () -> userServiceImpl.deleteUserByID(0));
    }


}
