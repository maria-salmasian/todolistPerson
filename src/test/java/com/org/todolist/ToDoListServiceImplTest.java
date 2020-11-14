package com.org.todolist;

import com.org.todolist.core.model.ToDoListModel;
import com.org.todolist.core.service.exception.ToDoListNotFoundException;
import com.org.todolist.core.service.exception.ValidationException;
import com.org.todolist.core.service.impl.ToDoListServiceImpl;
import com.org.todolist.infrastructure.entity.*;
import com.org.todolist.infrastructure.repository.ToDoListRepository;
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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


public class ToDoListServiceImplTest extends TodolistApplicationTests {

    @Mock
    private ToDoListRepository toDoListRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ToDoListServiceImpl toDoListService;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void getOrderedToDoListItemsOK() {
        List<ToDoListModel> expected = Arrays.asList(new ToDoListModel(0, 0, 0, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description"));

        ToDoList toDoList = new ToDoList();
        toDoList.setId(0);
        Status status = new Status();
        status.setId(0);
        status.setStatus(StatusEnum.IN_PROGRESS);
        status.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList.setStatus(status);
        toDoList.setDeleted(false);
        toDoList.setDescription("description");
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
        profession.setProfession(ProfessionEnum.DANCER);
        profession.setUsers(Arrays.asList(new User()));
        user.setProfession(profession);
        user.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList.setUser(user);
        List<ToDoList> toDoLists = Arrays.asList(toDoList);
        Mockito.when(toDoListRepository.findAll()).thenReturn(toDoLists);

        ToDoListModel toDoListModel = new ToDoListModel(0, 0, 0, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description");
        Mockito.when(modelMapper.map(toDoList, ToDoListModel.class)).thenReturn(toDoListModel);

        List<ToDoListModel> result = toDoListService.getOrderedToDoListItems();

        assertEquals(expected, result);
    }

    @Test
    void getToDoListItemsBasedOnStatusOK() {
        List<ToDoListModel> expected = Arrays.asList(new ToDoListModel(0, 0, 0, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description"));

        ToDoList toDoList = new ToDoList();
        toDoList.setId(0);
        Status status = new Status();
        status.setId(0);
        status.setStatus(StatusEnum.IN_PROGRESS);
        status.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList.setStatus(status);
        toDoList.setDescription("description");
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
        profession.setProfession(ProfessionEnum.DANCER);
        profession.setUsers(Arrays.asList(new User()));
        user.setProfession(profession);
        user.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList.setUser(user);
        List<ToDoList> toDoLists = Arrays.asList(toDoList);
        Mockito.when(toDoListRepository.findAllByStatus_Status(StatusEnum.IN_PROGRESS)).thenReturn(toDoLists);

        ToDoListModel toDoListModel = new ToDoListModel(0, 0, 0, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description");
        Mockito.when(modelMapper.map(toDoList, ToDoListModel.class)).thenReturn(toDoListModel);

        List<ToDoListModel> result = toDoListService.getToDoListItemsBasedOnStatus(StatusEnum.IN_PROGRESS);

        assertEquals(expected, result);
    }

    @Test
    void getToDoListByIDOK() throws Exception {
        ToDoListModel expected = new ToDoListModel(0, 0, 0, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description");

        ToDoList toDoList1 = new ToDoList();
        toDoList1.setId(0);
        Status status = new Status();
        status.setId(0);
        status.setStatus(StatusEnum.IN_PROGRESS);
        status.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList1.setStatus(status);
        toDoList1.setDescription("description");
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
        profession.setProfession(ProfessionEnum.DANCER);
        profession.setUsers(Arrays.asList(new User()));
        user.setProfession(profession);
        user.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList1.setUser(user);
        Optional<ToDoList> toDoList = Optional.of(toDoList1);
        Mockito.when(toDoListRepository.findById(0)).thenReturn(toDoList);
        ToDoListModel toDoListModel = new ToDoListModel(0, 0, 0, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description");
        Mockito.when(modelMapper.map(toDoList1, ToDoListModel.class)).thenReturn(toDoListModel);

        ToDoListModel result = toDoListService.getToDoListByID(0);

        assertEquals(expected, result);
    }

    @Test
    void getToDoListByIDThrowsToDoListNotFoundException() {
        ToDoList toDoList1 = new ToDoList();
        toDoList1.setId(0);
        Status status = new Status();
        status.setId(0);
        status.setStatus(StatusEnum.IN_PROGRESS);
        status.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList1.setStatus(status);
        toDoList1.setDescription("description");
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
        profession.setProfession(ProfessionEnum.DANCER);
        profession.setUsers(Arrays.asList(new User()));
        user.setProfession(profession);
        user.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList1.setUser(user);
        Optional<ToDoList> toDoList = Optional.empty();
        Mockito.when(toDoListRepository.findById(0)).thenReturn(toDoList);
        ToDoListModel toDoListModel = new ToDoListModel(0, 0, 0, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description");
        Mockito.when(modelMapper.map(toDoList1, ToDoListModel.class)).thenReturn(toDoListModel);

        assertThrows(ToDoListNotFoundException.class, () -> toDoListService.getToDoListByID(0));
    }


    @Test
    void getActiveToDoListItemsOK() {
        List<ToDoListModel> expected = Arrays.asList(new ToDoListModel(0, 0, 1, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description"));

        ToDoList toDoList = new ToDoList();
        toDoList.setId(0);
        Status status = new Status();
        status.setId(0);
        status.setStatus(StatusEnum.IN_PROGRESS);
        status.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList.setStatus(status);
        toDoList.setDescription("description");
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
        profession.setProfession(ProfessionEnum.DANCER);
        profession.setUsers(Arrays.asList(new User()));
        user.setProfession(profession);
        user.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList.setUser(user);
        List<ToDoList> toDoLists = Arrays.asList(toDoList);
        Mockito.when(toDoListRepository.findAll()).thenReturn(toDoLists);
        ToDoListModel toDoListModel = new ToDoListModel(0, 0, 1, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description");
        Mockito.when(modelMapper.map(toDoList, ToDoListModel.class)).thenReturn(toDoListModel);
        List<ToDoListModel> result = toDoListService.getActiveToDoListItems();

        assertEquals(expected, result);
    }

    @Test
    void saveToDoListOK() throws Exception {
        ToDoListModel toDoListModel = new ToDoListModel(0, 0, 0, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description");
        ToDoList expected = new ToDoList();
        expected.setId(0);
        Status status = new Status();
        status.setId(0);
        status.setStatus(StatusEnum.IN_PROGRESS);
        status.setToDoItems(Arrays.asList(new ToDoList()));
        expected.setStatus(status);
        expected.setDeleted(false);
        expected.setDescription("description");
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
        profession.setProfession(ProfessionEnum.DANCER);
        profession.setUsers(Arrays.asList(new User()));
        user.setProfession(profession);
        user.setToDoItems(Arrays.asList(new ToDoList()));
        expected.setUser(user);

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
        ToDoList toDoList = new ToDoList();
        toDoList.setId(0);
        Status status1 = new Status();
        status1.setId(0);
        status1.setStatus(StatusEnum.IN_PROGRESS);
        status1.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList.setStatus(status1);
        toDoList.setDeleted(false);
        toDoList.setDescription("description");
        toDoList.setUser(new User());
        user2.setToDoItems(Arrays.asList(toDoList));
        Optional<User> user1 = Optional.of(user2);
        Mockito.when(userRepository.findById(0)).thenReturn(user1);

        ToDoList toDoList1 = new ToDoList();
        toDoList1.setId(0);
        Status status2 = new Status();
        status2.setId(0);
        status2.setStatus(StatusEnum.IN_PROGRESS);
        status2.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList1.setStatus(status2);
        toDoList1.setDescription("description");
        User user3 = new User();
        user3.setId(0);
        user3.setPassportNo(0L);
        user3.setName("name");
        user3.setSurname("surname");
        user3.setEmail("email");
        user3.setSalary(0L);
        user3.setAge(0);
        Gender gender2 = new Gender();
        gender2.setId(0);
        gender2.setGender(GenderEnum.FEMALE);
        gender2.setUsers(Arrays.asList(new User()));
        user3.setGender(gender2);
        Profession profession2 = new Profession();
        profession2.setId(0);
        profession2.setProfession(ProfessionEnum.DANCER);
        profession2.setUsers(Arrays.asList(new User()));
        user3.setProfession(profession2);
        user3.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList1.setUser(user3);
        Mockito.when(modelMapper.map(toDoListModel, ToDoList.class)).thenReturn(toDoList1);

        ToDoList toDoList2 = new ToDoList();
        toDoList2.setId(0);
        Status status3 = new Status();
        status3.setId(0);
        status3.setStatus(StatusEnum.IN_PROGRESS);
        status3.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList2.setStatus(status3);
        toDoList2.setDescription("description");
        User user4 = new User();
        user4.setId(0);
        user4.setPassportNo(0L);
        user4.setName("name");
        user4.setSurname("surname");
        user4.setEmail("email");
        user4.setSalary(0L);
        user4.setAge(0);
        Gender gender3 = new Gender();
        gender3.setId(0);
        gender3.setGender(GenderEnum.FEMALE);
        gender3.setUsers(Arrays.asList(new User()));
        user4.setGender(gender3);
        Profession profession3 = new Profession();
        profession3.setId(0);
        profession3.setProfession(ProfessionEnum.DANCER);
        profession3.setUsers(Arrays.asList(new User()));
        user4.setProfession(profession3);
        user4.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList2.setUser(user4);
        Mockito.when(toDoListRepository.save(toDoList1)).thenReturn(toDoList2);
        ToDoList result = toDoListService.saveToDoList(toDoListModel);

        assertEquals(expected, result);
    }


    @Test
    void saveToDoListThrowsValidationException() {
        ToDoListModel toDoListModel = null;

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
        toDoList.setDescription("description");
        toDoList.setUser(new User());
        user1.setToDoItems(Arrays.asList(toDoList));
        Optional<User> user = Optional.of(user1);
        Mockito.when(userRepository.findById(0)).thenReturn(user);

        ToDoList toDoList1 = new ToDoList();
        toDoList1.setId(0);
        Status status1 = new Status();
        status1.setId(0);
        status1.setStatus(StatusEnum.IN_PROGRESS);
        status1.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList1.setStatus(status1);
        toDoList1.setDescription("description");
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
        user2.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList1.setUser(user2);
        Mockito.when(modelMapper.map(toDoListModel, ToDoList.class)).thenReturn(toDoList1);

        ToDoList toDoList2 = new ToDoList();
        toDoList2.setId(0);
        Status status2 = new Status();
        status2.setId(0);
        status2.setStatus(StatusEnum.IN_PROGRESS);
        status2.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList2.setStatus(status2);
        toDoList2.setDescription("description");
        User user3 = new User();
        user3.setId(0);
        user3.setPassportNo(0L);
        user3.setName("name");
        user3.setSurname("surname");
        user3.setEmail("email");
        user3.setSalary(0L);
        user3.setAge(0);
        Gender gender2 = new Gender();
        gender2.setId(0);
        gender2.setGender(GenderEnum.FEMALE);
        gender2.setUsers(Arrays.asList(new User()));
        user3.setGender(gender2);
        Profession profession2 = new Profession();
        profession2.setId(0);
        profession2.setProfession(ProfessionEnum.DANCER);
        profession2.setUsers(Arrays.asList(new User()));
        user3.setProfession(profession2);
        user3.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList2.setUser(user3);
        Mockito.when(toDoListRepository.save(toDoList1)).thenReturn(toDoList2);

        assertThrows(ValidationException.class, () -> toDoListService.saveToDoList(toDoListModel));
    }


    @Test
    void updateToDoListByIDOK() throws Exception {
        ToDoListModel toDoListModel = new ToDoListModel(0, 0, 0, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description");
        ToDoList expected = new ToDoList();
        expected.setId(0);
        Status status = new Status();
        status.setId(0);
        status.setStatus(StatusEnum.IN_PROGRESS);
        status.setToDoItems(Arrays.asList(new ToDoList()));
        expected.setStatus(status);
        expected.setDescription("description");
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
        profession.setProfession(ProfessionEnum.DANCER);
        profession.setUsers(Arrays.asList(new User()));
        user.setProfession(profession);
        user.setToDoItems(Arrays.asList(new ToDoList()));
        expected.setUser(user);

        ToDoList toDoList1 = new ToDoList();
        toDoList1.setId(0);
        Status status1 = new Status();
        status1.setId(0);
        status1.setStatus(StatusEnum.IN_PROGRESS);
        status1.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList1.setStatus(status1);
        toDoList1.setDescription("description");
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
        profession1.setUsers(Arrays.asList(new User()));
        user1.setProfession(profession1);
        user1.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList1.setUser(user1);
        Optional<ToDoList> toDoList = Optional.of(toDoList1);
        Mockito.when(toDoListRepository.findById(0)).thenReturn(toDoList);

        ToDoList toDoList2 = new ToDoList();
        toDoList2.setId(0);
        Status status2 = new Status();
        status2.setId(0);
        status2.setStatus(StatusEnum.IN_PROGRESS);
        status2.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList2.setStatus(status2);
        toDoList2.setDescription("description");
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
        gender2.setGender(GenderEnum.FEMALE);
        gender2.setUsers(Arrays.asList(new User()));
        user2.setGender(gender2);
        Profession profession2 = new Profession();
        profession2.setId(0);
        profession2.setProfession(ProfessionEnum.DANCER);
        profession2.setUsers(Arrays.asList(new User()));
        user2.setProfession(profession2);
        user2.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList2.setUser(user2);
        Mockito.when(modelMapper.map(
                toDoListModel, ToDoList.class)).thenReturn(toDoList2);


        User user4 = new User();
        user4.setId(0);
        user4.setPassportNo(0L);
        user4.setName("name");
        user4.setSurname("surname");
        user4.setEmail("email");
        user4.setSalary(0L);
        user4.setAge(0);
        Gender gender3 = new Gender();
        gender3.setId(0);
        gender3.setGender(GenderEnum.FEMALE);
        gender3.setUsers(Arrays.asList(new User()));
        user4.setGender(gender3);
        Profession profession3 = new Profession();
        profession3.setId(0);
        profession3.setProfession(ProfessionEnum.DANCER);

        profession3.setUsers(Arrays.asList(new User()));
        user4.setProfession(profession3);
        ToDoList toDoList3 = new ToDoList();
        toDoList3.setId(0);
        Status status3 = new Status();
        status3.setId(0);
        status3.setStatus(StatusEnum.IN_PROGRESS);
        status3.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList3.setStatus(status3);
        toDoList3.setDescription("description");
        toDoList3.setUser(new User());
        user4.setToDoItems(Arrays.asList(toDoList3));
        Optional<User> user3 = Optional.of(user4);
        Mockito.when(userRepository.findById(0)).thenReturn(user3);

        ToDoList toDoList4 = new ToDoList();
        toDoList4.setId(0);
        Status status4 = new Status();
        status4.setId(0);
        status4.setStatus(StatusEnum.IN_PROGRESS);
        status4.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList4.setStatus(status4);
        toDoList4.setDescription("description");
        User user5 = new User();
        user5.setId(0);
        user5.setPassportNo(0L);
        user5.setName("name");
        user5.setSurname("surname");
        user5.setEmail("email");
        user5.setSalary(0L);
        user5.setAge(0);
        Gender gender4 = new Gender();
        gender4.setId(0);
        gender4.setGender(GenderEnum.FEMALE);
        gender4.setUsers(Arrays.asList(new User()));
        user5.setGender(gender4);
        Profession profession4 = new Profession();
        profession4.setId(0);
        profession4.setProfession(ProfessionEnum.DANCER);
        profession4.setUsers(Arrays.asList(new User()));
        user5.setProfession(profession4);
        user5.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList4.setUser(user5);
        Mockito.when(toDoListRepository.save(toDoList2)).thenReturn(toDoList4);

        ToDoList result = toDoListService.updateToDoListByID(0, toDoListModel);

        assertEquals(expected, result);
        verify(toDoListRepository).delete(toDoList1);
    }


    @Test
    void deleteToDoListByIDOK() throws Exception {

        ToDoList toDoList1 = new ToDoList();
        toDoList1.setId(0);
        Status status = new Status();
        status.setId(0);
        status.setStatus(StatusEnum.IN_PROGRESS);
        status.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList1.setStatus(status);
        toDoList1.setDeleted(false);
        toDoList1.setDescription("description");
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
        profession.setProfession(ProfessionEnum.DANCER);
        profession.setUsers(Arrays.asList(new User()));
        user.setProfession(profession);
        user.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList1.setUser(user);
        Optional<ToDoList> toDoList = Optional.of(toDoList1);
        Mockito.when(toDoListRepository.findById(0)).thenReturn(toDoList);

        ToDoList toDoList2 = new ToDoList();
        toDoList2.setId(0);
        Status status1 = new Status();
        status1.setId(0);
        status1.setStatus(StatusEnum.IN_PROGRESS);
        status1.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList2.setStatus(status1);
        toDoList2.setDescription("description");
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
        profession1.setUsers(Arrays.asList(new User()));
        user1.setProfession(profession1);
        user1.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList2.setUser(user1);
        Mockito.when(toDoListRepository.save(new ToDoList())).thenReturn(toDoList2);

        toDoListService.deleteToDoListByID(0);

    }

    @Test
    void deleteToDoListByIDThrowsToDoListNotFoundException() {
        ToDoList toDoList1 = new ToDoList();
        toDoList1.setId(0);
        Status status = new Status();
        status.setId(0);
        status.setStatus(StatusEnum.IN_PROGRESS);
        status.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList1.setStatus(status);
        toDoList1.setDeleted(false);
        toDoList1.setDescription("description");
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
        profession.setProfession(ProfessionEnum.DANCER);
        profession.setUsers(Arrays.asList(new User()));
        user.setProfession(profession);
        user.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList1.setUser(user);
        Optional<ToDoList> toDoList = Optional.empty();
        Mockito.when(toDoListRepository.findById(0)).thenReturn(toDoList);

        ToDoList toDoList2 = new ToDoList();
        toDoList2.setId(0);
        Status status1 = new Status();
        status1.setId(0);
        status1.setStatus(StatusEnum.IN_PROGRESS);
        status1.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList2.setStatus(status1);
        toDoList2.setDeleted(false);
        toDoList2.setDescription("description");
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
        profession1.setUsers(Arrays.asList(new User()));
        user1.setProfession(profession1);
        user1.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList2.setUser(user1);
        Mockito.when(toDoListRepository.save(toDoList1)).thenReturn(toDoList2);

        assertThrows(ToDoListNotFoundException.class, () -> toDoListService.deleteToDoListByID(0));
    }


    @Test
    void deleteAllToDoListOK() {
        ToDoList toDoList = new ToDoList();
        toDoList.setId(0);
        Status status = new Status();
        status.setId(0);
        status.setStatus(StatusEnum.IN_PROGRESS);
        status.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList.setStatus(status);
        toDoList.setDescription("description");
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
        profession.setProfession(ProfessionEnum.DANCER);
        profession.setUsers(Arrays.asList(new User()));
        user.setProfession(profession);
        user.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList.setUser(user);
        List<ToDoList> toDoLists = Arrays.asList(toDoList);
        Mockito.when(toDoListRepository.findAll()).thenReturn(toDoLists);

        ToDoList toDoList1 = new ToDoList();
        toDoList1.setId(0);
        Status status1 = new Status();
        status1.setId(0);
        status1.setStatus(StatusEnum.IN_PROGRESS);
        status1.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList1.setStatus(status1);
        toDoList1.setDescription("description");
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
        profession1.setUsers(Arrays.asList(new User()));
        user1.setProfession(profession1);
        user1.setToDoItems(Arrays.asList(new ToDoList()));
        toDoList1.setUser(user1);
        Mockito.when(toDoListRepository.save(new ToDoList())).thenReturn(toDoList1);

        toDoListService.deleteAllToDoList();

    }
}
