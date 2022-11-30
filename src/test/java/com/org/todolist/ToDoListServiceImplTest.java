package com.org.todolist;

import com.org.todolist.core.model.ToDoListModel;
import com.org.todolist.core.service.exception.ToDoListNotFoundException;
import com.org.todolist.core.service.exception.ValidationException;
import com.org.todolist.core.service.impl.ToDoListServiceImpl;
import com.org.todolist.infrastructure.entity.ToDoList;
import com.org.todolist.infrastructure.entity.User;
import com.org.todolist.infrastructure.repository.ToDoListRepository;
import com.org.todolist.infrastructure.repository.UserRepository;
import com.org.todolist.utils.enumeration.StatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;


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
    }

    @Test
    void getOrderedToDoListItemsOK() {
        List<ToDoListModel> expected = Collections.singletonList(new ToDoListModel(0, 0, 0, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description"));
        ToDoList toDoList = createToDoList();
        List<ToDoList> toDoLists = Collections.singletonList(toDoList);
        Mockito.when(toDoListRepository.findAll()).thenReturn(toDoLists);
        ToDoListModel toDoListModel = new ToDoListModel(0, 0, 0, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description");
        Mockito.when(modelMapper.map(toDoList, ToDoListModel.class)).thenReturn(toDoListModel);
        List<ToDoListModel> result = toDoListService.getOrderedToDoListItems();


        assertEquals(expected, result);
    }

    @Test
    void getToDoListItemsBasedOnStatusOK() {
        List<ToDoListModel> expected = Collections.singletonList(new ToDoListModel(0, 0, 0, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description"));
        ToDoList toDoList = createToDoList();
        List<ToDoList> toDoLists = Collections.singletonList(toDoList);
        Mockito.when(toDoListRepository.findAllByStatus_Status(StatusEnum.IN_PROGRESS)).thenReturn(toDoLists);
        ToDoListModel toDoListModel = new ToDoListModel(0, 0, 0, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description");
        Mockito.when(modelMapper.map(toDoList, ToDoListModel.class)).thenReturn(toDoListModel);
        List<ToDoListModel> result = toDoListService.getToDoListItemsBasedOnStatus(StatusEnum.IN_PROGRESS);

        assertEquals(expected, result);
    }

    @Test
    void getToDoListByIDOK() throws Exception {
        ToDoListModel expected = new ToDoListModel(0, 0, 0, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description");

        ToDoList toDoList1 = createToDoList();
        Optional<ToDoList> toDoList = Optional.of(toDoList1);
        Mockito.when(toDoListRepository.findById(0)).thenReturn(toDoList);
        ToDoListModel toDoListModel = new ToDoListModel(0, 0, 0, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description");
        Mockito.when(modelMapper.map(toDoList1, ToDoListModel.class)).thenReturn(toDoListModel);
        ToDoListModel result = toDoListService.getToDoListByID(0);

        assertEquals(expected, result);
    }

    @Test
    void getToDoListByIDThrowsToDoListNotFoundException() {
        ToDoList toDoList1 =  createToDoList();
        Optional<ToDoList> toDoList = Optional.empty();
        Mockito.when(toDoListRepository.findById(0)).thenReturn(toDoList);
        ToDoListModel toDoListModel = new ToDoListModel(0, 0, 0, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description");
        Mockito.when(modelMapper.map(toDoList1, ToDoListModel.class)).thenReturn(toDoListModel);

        assertThrows(ToDoListNotFoundException.class, () -> toDoListService.getToDoListByID(0));
    }


    @Test
    void getActiveToDoListItemsOK() {
        List<ToDoListModel> expected = Collections.singletonList(new ToDoListModel(0, 0, 1, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description"));
        ToDoList toDoList = createToDoList();
        List<ToDoList> toDoLists = Collections.singletonList(toDoList);
        Mockito.when(toDoListRepository.findAll()).thenReturn(toDoLists);
        ToDoListModel toDoListModel = new ToDoListModel(0, 0, 1, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description");
        Mockito.when(modelMapper.map(toDoList, ToDoListModel.class)).thenReturn(toDoListModel);
        List<ToDoListModel> result = toDoListService.getActiveToDoListItems();

        assertEquals(expected, result);
    }


    @Test
    void getActiveAndOrderedToDoListItemsOK() {
        List<ToDoListModel> expected = Collections.singletonList(new ToDoListModel(0, 0, 1, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description"));
        ToDoList toDoList = createToDoList();
        List<ToDoList> toDoLists = Collections.singletonList(toDoList);
        Mockito.when(toDoListRepository.findAll()).thenReturn(toDoLists);
        ToDoListModel toDoListModel = new ToDoListModel(0, 0, 1, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description");
        Mockito.when(modelMapper.map(toDoList, ToDoListModel.class)).thenReturn(toDoListModel);
        List<ToDoListModel> result = toDoListService.getActiveAndOrderedToDoListItems();

        assertEquals(expected, result);
    }

    @Test
    void saveToDoListOK() throws Exception {
        ToDoListModel toDoListModel = new ToDoListModel(0, 0, 0, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description");
        ToDoList expected = createToDoList();
        User user2 = createUser();
        Optional<User> user1 = Optional.of(user2);
        Mockito.when(userRepository.findById(0)).thenReturn(user1);
        ToDoList toDoList1 = createToDoList();
        Mockito.when(modelMapper.map(toDoListModel, ToDoList.class)).thenReturn(toDoList1);
        ToDoList toDoList2 =createToDoList();
        Mockito.when(toDoListRepository.save(toDoList1)).thenReturn(toDoList2);
        ToDoList result = toDoListService.saveToDoList(toDoListModel);

        assertEquals(expected, result);
    }


    @Test
    void saveToDoListThrowsValidationException() {
        ToDoListModel toDoListModel = null;
        User user1 = createUser();
        Optional<User> user = Optional.of(user1);
        Mockito.when(userRepository.findById(0)).thenReturn(user);
        ToDoList toDoList1 = createToDoList();
        Mockito.when(modelMapper.map(toDoListModel, ToDoList.class)).thenReturn(toDoList1);
        ToDoList toDoList2 = createToDoList();
        Mockito.when(toDoListRepository.save(toDoList1)).thenReturn(toDoList2);

        assertThrows(ValidationException.class, () -> toDoListService.saveToDoList(toDoListModel));
    }


    @Test
    void updateToDoListByIDOK() throws Exception {
        ToDoListModel toDoListModel = new ToDoListModel(0, 0, 0, false, LocalDateTime.of(2020, 1, 1, 0, 0, 0), LocalDateTime.of(2020, 1, 1, 0, 0, 0), "description");
        ToDoList expected = createToDoList();
        ToDoList toDoList1 = createToDoList();
        Optional<ToDoList> toDoList = Optional.of(toDoList1);
        Mockito.when(toDoListRepository.findById(0)).thenReturn(toDoList);
        ToDoList toDoList2 = createToDoList();
        Mockito.when(modelMapper.map(
                toDoListModel, ToDoList.class)).thenReturn(toDoList2);
        User user4 = createUser();
        Optional<User> user3 = Optional.of(user4);
        Mockito.when(userRepository.findById(0)).thenReturn(user3);
        ToDoList toDoList4 = createToDoList();
        Mockito.when(toDoListRepository.save(toDoList2)).thenReturn(toDoList4);
        ToDoList result = toDoListService.updateToDoListByID(0, toDoListModel);

        assertEquals(expected, result);
        verify(toDoListRepository).delete(toDoList1);
    }



    @Test
    void deleteToDoListByIDOK() throws Exception {
        ToDoList toDoList1 = createToDoList();
        Optional<ToDoList> toDoList = Optional.of(toDoList1);
        Mockito.when(toDoListRepository.findById(0)).thenReturn(toDoList);
        ToDoList toDoList2 = createToDoList();
        Mockito.when(toDoListRepository.save(new ToDoList())).thenReturn(toDoList2);
        toDoListService.deleteToDoListByID(0);

    }

    @Test
    void deleteToDoListByIDThrowsToDoListNotFoundException() {
        ToDoList toDoList1 = createToDoList();
        Optional<ToDoList> toDoList = Optional.empty();
        Mockito.when(toDoListRepository.findById(0)).thenReturn(toDoList);
        ToDoList toDoList2 = createToDoList();
        Mockito.when(toDoListRepository.save(toDoList1)).thenReturn(toDoList2);

        assertThrows(ToDoListNotFoundException.class, () -> toDoListService.deleteToDoListByID(0));
    }


    @Test
    void deleteAllToDoListOK() {
        ToDoList toDoList = createToDoList();
        List<ToDoList> toDoLists = Collections.singletonList(toDoList);
        Mockito.when(toDoListRepository.findAll()).thenReturn(toDoLists);
        ToDoList toDoList1 =createToDoList();
        Mockito.when(toDoListRepository.save(new ToDoList())).thenReturn(toDoList1);

        toDoListService.deleteAllToDoList();

    }
}
