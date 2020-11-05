package com.org.todolist.core.service.impl;

import com.org.todolist.core.model.ToDoListModel;
import com.org.todolist.core.service.ToDoListService;
import com.org.todolist.infrastructure.entity.Status;
import com.org.todolist.infrastructure.entity.ToDoList;
import com.org.todolist.infrastructure.entity.User;
import com.org.todolist.infrastructure.repository.statusRepository.StatusRepository;
import com.org.todolist.infrastructure.repository.toDoListRepository.ToDoListRepository;
import com.org.todolist.infrastructure.repository.userRepository.UserRepository;
import com.org.todolist.utils.enumeration.StatusEnum;
import com.org.todolist.ws.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ToDoListServiceImpl implements ToDoListService {

    @Autowired
    private ToDoListRepository toDoListRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<ToDoListModel> getOrderedToDoListItems() throws NotFoundException {
        log.info("method getOrderedToDoListItems invoked from ToDoListService");

        List<ToDoListModel> userList = (toDoListRepository
                .findAll())
                .stream()
                .map(x -> modelMapper.map(x, ToDoListModel.class))
                .sorted(Comparator.comparing(ToDoListModel::getUserId))
                .collect(Collectors.toList());

        if (!userList.isEmpty())
            return userList;
        else throw new NotFoundException("No toDoList found");

    }


    @Override
    public List<ToDoListModel> getToDoListItemsBasedOnStatus(int status) throws NotFoundException {
        log.info("method getToDoListItemsBasedOnStatus invoked from ToDoListService");

        List<ToDoListModel> userList = (toDoListRepository
                .findAllByStatusId(status)
                .stream()
                .map(x -> modelMapper.map(x, ToDoListModel.class))
                .collect(Collectors.toList()));
        if (!userList.isEmpty())
            return userList;
        else throw new NotFoundException("No toDoList found");
    }


    @Override
    public ToDoListModel getToDoListByID(int id) throws NotFoundException {
        log.info("method getToDoListByID invoked from ToDoListService");

        ToDoList toDoList = toDoListRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("toDoList not found for this id :: " + id));
        ToDoListModel toDoListModel = new ToDoListModel();
        toDoListModel.setUserId(userRepository.findUserById(toDoList.getUser().getId()).getId());
        toDoListModel.setStatusId(statusRepository.findStatusById(toDoList.getStatus().getId()).getId());
        toDoListModel = modelMapper.map(toDoList, ToDoListModel.class);

        return toDoListModel;
    }

    @Override
    public List<ToDoListModel> getActiveToDoListItems() throws NotFoundException {
        log.info("method getActiveToDoListItems invoked from ToDoListService");

        List<ToDoListModel> userList = (toDoListRepository
                .findAll())
                .stream()
                .map(x -> modelMapper.map(x, ToDoListModel.class))
                .filter(toDoListDTO -> toDoListDTO.getStatusId() == (StatusEnum.IN_PROGRESS).getId())
                .collect(Collectors.toList());
        if (!userList.isEmpty())
            return userList;
        else throw new NotFoundException("No toDoList found");

    }


    @Override
    public ToDoList saveToDoList(ToDoListModel toDoListModel) throws NotFoundException {
        log.info("method saveToDoList invoked from ToDoListService");

        if (toDoListModel == null)
            throw new NotFoundException("toDoList not found to save");
        ToDoList toDoList = modelMapper.map(toDoListModel, ToDoList.class);
        User user = userRepository.findById(toDoListModel.getUserId())
                .orElseThrow(() -> new NotFoundException("User specified not found"));
        Status status = (statusRepository.findStatusById(toDoListModel.getStatusId()));

        toDoList.setStatus(status);
        toDoList.setUser(user);
        toDoList.setCreatedAt(LocalDateTime.now());
        toDoList.setUpdatedAt(LocalDateTime.now());
        toDoList.setDeleted(false);
        return toDoListRepository.save(toDoList);
    }


    @Override
    public ToDoList updateToDoListByID(int id, ToDoListModel toDoListModel) throws NotFoundException {
        log.info("method updateToDoListByID invoked from ToDoListService for id{}", id);

        toDoListRepository.findById(id).orElseThrow(() ->
                new NotFoundException("toDoList not found for this id :: " + id));
        User user = userRepository.findById(toDoListModel.getUserId()).orElseThrow(() -> new NotFoundException("User not found"));
        Status status = (statusRepository.findStatusById(toDoListModel.getStatusId()));


        ToDoList toDoList = modelMapper.map(toDoListModel, ToDoList.class);
        toDoList.setStatus(status);
        toDoList.setUser(user);
        toDoList.setUpdatedAt(LocalDateTime.now());
        return toDoListRepository.save(toDoList);
    }

    @Override
    public void deleteToDoListByID(int id) throws NotFoundException {
        log.info("method deleteToDoListByID invoked from ToDoListService for id{}", id);

        ToDoList toDoList = toDoListRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("toDoList not found for this id :: " + id));
        toDoList.setDeleted(true);
        toDoListRepository.save(toDoList);
    }

    @Override
    public void deleteAllToDoList() {
        log.info("method deleteAllToDoList invoked from ToDoListService ");

        List<ToDoList> all = toDoListRepository.findAll();
        all.stream().forEach(item -> {
            item.setDeleted(true);
            toDoListRepository.save(item);

        });
    }


}
