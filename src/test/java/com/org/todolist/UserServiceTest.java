package com.org.todolist;

import com.org.todolist.core.model.ToDoListModel;
import com.org.todolist.core.model.UserModel;
import com.org.todolist.core.service.exception.UserNotFoundException;
import com.org.todolist.core.service.exception.ValidationException;
import com.org.todolist.core.service.impl.UserServiceImpl;
import com.org.todolist.infrastructure.entity.*;
import com.org.todolist.infrastructure.repository.UserRepository;
import com.org.todolist.utils.enumeration.GenderEnum;
import com.org.todolist.utils.enumeration.ProfessionEnum;
import com.org.todolist.utils.enumeration.StatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest extends TodolistApplicationTests {

    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void testGetUsersOK() {
        List<UserModel> expected = Arrays.asList(new UserModel(0, "name", "surname", new BigDecimal("0.00"), "email", 0, 0, 0, 0L, false, Arrays.asList(new ToDoListModel(0, 0, 0, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description")), LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0)));
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
        List<User> userList = Arrays.asList(user);
        Mockito.when(userRepository.findAll()).thenReturn(userList);

        UserModel userModel = new UserModel(0, "name", "surname", new BigDecimal("0.00"), "email", 0, 0, 0, 0L, false, Arrays.asList(new ToDoListModel(0, 0, 0, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description")), LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        Mockito.when(modelMapper.map(user, UserModel.class)).thenReturn(userModel);

        List<UserModel> result = userServiceImpl.getUsers();

        assertEquals(expected, result);
    }

    @Test
    void getUserByIDOK() throws Exception {
        UserModel expected = new UserModel(0, "name", "surname", new BigDecimal("0.00"), "email", 0, 0, 0, 0L, false, Arrays.asList(new ToDoListModel(0, 0, 0, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description")), LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        User user1 = new User();
        user1.setId(0);
        user1.setPassportNo(0L);
        user1.setName("name");
        user1.setSurname("surname");
        user1.setEmail("email");
        user1.setSalary(0L);
        user1.setAge(0);
        Gender gender = new Gender();
        gender.setId(0);
        gender.setGender(GenderEnum.FEMALE);
        gender.setUsers(Arrays.asList(new User()));
        user1.setGender(gender);
        Profession profession = new Profession();
        profession.setId(0);
        profession.setProfession(ProfessionEnum.DANCER);
        profession.setUsers(Arrays.asList(new User()));
        user1.setProfession(profession);
        ToDoList toDoList = new ToDoList();
        toDoList.setId(0);
        Status status = new Status();
        status.setId(0);
        status.setStatus(StatusEnum.IN_PROGRESS);
        status.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList.setStatus(status);
        toDoList.setDeleted(false);
        toDoList.setDescription("description");
        toDoList.setUser(new User());
        user1.setToDoItems(Arrays.asList(toDoList));
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
        User expected = new User();
        expected.setId(0);
        expected.setPassportNo(0L);
        expected.setName("name");
        expected.setSurname("surname");
        expected.setEmail("email");
        expected.setSalary(0L);
        expected.setAge(0);
        Gender gender = new Gender();
        gender.setId(0);
        gender.setGender(GenderEnum.FEMALE);
        gender.setUsers(Arrays.asList(new User()));
        expected.setGender(gender);
        Profession profession = new Profession();
        profession.setId(0);
        profession.setProfession(ProfessionEnum.DANCER);
        profession.setUsers(Arrays.asList(new User()));
        expected.setProfession(profession);
        ToDoList toDoList = new ToDoList();
        toDoList.setId(0);
        Status status = new Status();
        status.setId(0);
        status.setStatus(StatusEnum.IN_PROGRESS);
        status.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList.setStatus(status);
        toDoList.setDescription("description");
        toDoList.setUser(new User());
        expected.setToDoItems(Arrays.asList(toDoList));

        User user = new User();
        user.setId(0);
        user.setPassportNo(0L);
        user.setName("name");
        user.setSurname("surname");
        user.setEmail("email");
        user.setSalary(0L);
        user.setAge(0);
        Gender gender1 = new Gender();
        gender1.setId(0);
        gender1.setGender(GenderEnum.FEMALE);
        gender1.setUsers(Arrays.asList(new User()));
        user.setGender(gender1);
        Profession profession1 = new Profession();
        profession1.setId(0);
        profession1.setProfession(ProfessionEnum.DANCER);
        profession1.setUsers(Arrays.asList(new User()));
        user.setProfession(profession1);
        ToDoList toDoList1 = new ToDoList();
        toDoList1.setId(0);
        Status status1 = new Status();
        status1.setId(0);
        status1.setStatus(StatusEnum.IN_PROGRESS);
        status1.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList1.setStatus(status1);
        toDoList1.setDeleted(false);
        toDoList1.setDescription("description");
        toDoList1.setUser(new User());
        user.setToDoItems(Arrays.asList(toDoList1));
        Mockito.when(modelMapper.map(userModel, User.class)).thenReturn(user);

        User user1 = new User();
        user1.setId(0);
        user1.setPassportNo(0L);
        user1.setName("name");
        user1.setSurname("surname");
        user1.setEmail("email");
        user1.setSalary(0L);
        user1.setAge(0);
        Gender gender2 = new Gender();
        gender2.setId(0);
        gender2.setGender(GenderEnum.FEMALE);
        gender2.setUsers(Arrays.asList(new User()));
        user1.setGender(gender2);
        Profession profession2 = new Profession();
        profession2.setId(0);
        profession2.setProfession(ProfessionEnum.DANCER);
        profession2.setUsers(Arrays.asList(new User()));
        user1.setProfession(profession2);
        ToDoList toDoList2 = new ToDoList();
        toDoList2.setId(0);
        Status status2 = new Status();
        status2.setId(0);
        status2.setStatus(StatusEnum.IN_PROGRESS);
        status2.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList2.setStatus(status2);
        toDoList2.setDescription("description");
        toDoList2.setUser(new User());
        user1.setToDoItems(Arrays.asList(toDoList2));
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
        User expected = new User();
        expected.setId(0);
        expected.setPassportNo(0L);
        expected.setName("name");
        expected.setSurname("surname");
        expected.setEmail("email");
        expected.setSalary(0L);
        expected.setAge(0);
        Gender gender = new Gender();
        gender.setId(0);
        gender.setGender(GenderEnum.FEMALE);
        gender.setUsers(Arrays.asList(new User()));
        expected.setGender(gender);
        Profession profession = new Profession();
        profession.setId(0);
        profession.setProfession(ProfessionEnum.DANCER);
        profession.setUsers(Arrays.asList(new User()));
        expected.setProfession(profession);
        ToDoList toDoList = new ToDoList();
        toDoList.setId(0);
        Status status = new Status();
        status.setId(0);
        status.setStatus(StatusEnum.IN_PROGRESS);
        status.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList.setStatus(status);
        toDoList.setDescription("description");
        toDoList.setUser(new User());
        expected.setToDoItems(Arrays.asList(toDoList));


        User user1 = new User();
        user1.setId(0);
        user1.setPassportNo(0L);
        user1.setName("name");
        user1.setSurname("surname");
        user1.setEmail("email");
        user1.setSalary(0L);
        user1.setAge(0);
        Gender gender1 = new Gender();
        gender1.setId(0);
        gender1.setGender(GenderEnum.FEMALE);
        gender1.setUsers(Arrays.asList(new User()));
        user1.setGender(gender1);
        Profession profession1 = new Profession();
        profession1.setId(0);
        profession1.setProfession(ProfessionEnum.DANCER);
        profession1.setDeleted(false);
        profession1.setUsers(Arrays.asList(new User()));
        user1.setProfession(profession1);
        ToDoList toDoList1 = new ToDoList();
        toDoList1.setId(0);
        Status status1 = new Status();
        status1.setId(0);
        status1.setStatus(StatusEnum.IN_PROGRESS);
        status1.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList1.setStatus(status1);
        toDoList1.setDescription("description");
        toDoList1.setUser(new User());
        user1.setToDoItems(Arrays.asList(toDoList1));

        Optional<User> user = Optional.of(user1);
        Mockito.when(userRepository.findById(0)).thenReturn(user);

        User user2 = new User();
        user2.setId(0);
        user2.setPassportNo(0L);
        user2.setName("name");
        user2.setSurname("surname");
        user2.setEmail("email");
        user2.setSalary(0L);
        user2.setAge(0);
        Gender gender2 = new Gender();
        gender2.setId(0);
        gender2.setUsers(Arrays.asList(new User()));
        user2.setGender(gender2);
        Profession profession2 = new Profession();
        profession2.setId(0);
        profession2.setProfession(ProfessionEnum.DANCER);
        profession2.setUsers(Arrays.asList(new User()));
        user2.setProfession(profession2);
        ToDoList toDoList2 = new ToDoList();
        toDoList2.setId(0);
        Status status2 = new Status();
        status2.setId(0);
        status2.setStatus(StatusEnum.IN_PROGRESS);
        status2.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList2.setStatus(status2);
        toDoList2.setDescription("description");
        toDoList2.setUser(new User());
        user2.setToDoItems(Arrays.asList(toDoList2));
        Mockito.when(modelMapper.map(userModel, User.class)).thenReturn(user2);

        User user3 = new User();
        user3.setId(0);
        user3.setPassportNo(0L);
        user3.setName("name");
        user3.setSurname("surname");
        user3.setEmail("email");
        user3.setSalary(0L);
        user3.setAge(0);
        Gender gender3 = new Gender();
        gender3.setId(0);
        gender3.setGender(GenderEnum.FEMALE);
        gender3.setUsers(Arrays.asList(new User()));
        user3.setGender(gender3);
        Profession profession3 = new Profession();
        profession3.setId(0);
        profession3.setProfession(ProfessionEnum.DANCER);
        profession3.setUsers(Arrays.asList(new User()));
        user3.setProfession(profession3);
        ToDoList toDoList3 = new ToDoList();
        toDoList3.setId(0);
        Status status3 = new Status();
        status3.setId(0);
        status3.setStatus(StatusEnum.IN_PROGRESS);
        status3.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList3.setStatus(status3);
        toDoList3.setDeleted(false);
        toDoList3.setDescription("description");
        toDoList3.setUser(new User());
        user3.setToDoItems(Arrays.asList(toDoList3));
        Mockito.when(userRepository.save(user2)).thenReturn(user3);

        User result = userServiceImpl.updateUserByID(0, userModel);

        assertEquals(expected, result);
        verify(userRepository).delete(user1);
    }


    @Test
    void deleteUserByIDOK() throws Exception {
        User user1 = new User();
        user1.setId(0);
        user1.setPassportNo(0L);
        user1.setName("name");
        user1.setSurname("surname");
        user1.setEmail("email");
        user1.setSalary(0L);
        user1.setAge(0);
        Gender gender = new Gender();
        gender.setId(0);
        gender.setGender(GenderEnum.FEMALE);
        gender.setUsers(Arrays.asList(new User()));
        user1.setGender(gender);
        Profession profession = new Profession();
        profession.setId(0);
        profession.setProfession(ProfessionEnum.DANCER);
        profession.setUsers(Arrays.asList(new User()));
        user1.setProfession(profession);
        ToDoList toDoList = new ToDoList();
        toDoList.setId(0);
        Status status = new Status();
        status.setId(0);
        status.setStatus(StatusEnum.IN_PROGRESS);
        status.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList.setStatus(status);
        toDoList.setDeleted(false);
        toDoList.setDescription("description");
        toDoList.setUser(new User());
        user1.setToDoItems(Arrays.asList(toDoList));
        Optional<User> user = Optional.of(user1);
        Mockito.when(userRepository.findById(0)).thenReturn(user);

        User user2 = new User();
        user2.setId(0);
        user2.setPassportNo(0L);
        user2.setName("name");
        user2.setSurname("surname");
        user2.setEmail("email");
        user2.setSalary(0L);
        user2.setAge(0);
        Gender gender1 = new Gender();
        gender1.setId(0);
        gender1.setGender(GenderEnum.FEMALE);
        gender1.setUsers(Arrays.asList(new User()));
        user2.setGender(gender1);
        Profession profession1 = new Profession();
        profession1.setId(0);
        profession1.setProfession(ProfessionEnum.DANCER);
        profession1.setUsers(Arrays.asList(new User()));
        user2.setProfession(profession1);
        ToDoList toDoList1 = new ToDoList();
        toDoList1.setId(0);
        Status status1 = new Status();
        status1.setId(0);
        status1.setStatus(StatusEnum.IN_PROGRESS);
        status1.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList1.setStatus(status1);
        toDoList1.setDeleted(false);
        toDoList1.setDescription("description");
        toDoList1.setUser(new User());
        user2.setToDoItems(Arrays.asList(toDoList1));
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
