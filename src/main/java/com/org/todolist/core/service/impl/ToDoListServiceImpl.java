package com.org.todolist.core.service.impl;

import com.org.todolist.core.model.ToDoListModel;
import com.org.todolist.core.service.ToDoListService;
import com.org.todolist.core.service.exception.ToDoListNotFoundException;
import com.org.todolist.core.service.exception.UserNotFoundException;
import com.org.todolist.core.service.exception.ValidationException;
import com.org.todolist.infrastructure.entity.ToDoList;
import com.org.todolist.infrastructure.repository.ToDoListRepository;
import com.org.todolist.infrastructure.repository.UserRepository;
import com.org.todolist.utils.enumeration.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;



    @Override
    public List<ToDoListModel> getOrderedToDoListItems()  {
        log.info("method getOrderedToDoListItems invoked from ToDoListService");
        List<ToDoListModel> toDoListModels = (toDoListRepository
                .findAll())
                .stream()
                .map(x -> modelMapper.map(x, ToDoListModel.class))
                .sorted(Comparator.comparing(ToDoListModel::getUserId))
                .collect(Collectors.toList());
     Assert.notEmpty(toDoListModels,"No toDoList found");
        return toDoListModels;
    }


    /**
     *
     * @param status
     * @return
     */
    @Override
    public List<ToDoListModel> getToDoListItemsBasedOnStatus(StatusEnum status) {
        log.info("method getToDoListItemsBasedOnStatus invoked from ToDoListService");
        List<ToDoListModel> toDoListModels = (toDoListRepository
                .findAllByStatus_Status(status)
                .stream()
                .map(x -> modelMapper.map(x, ToDoListModel.class))
                .collect(Collectors.toList()));
        Assert.notEmpty(toDoListModels,"No toDoList found");
        return toDoListModels;
    }


    /**
     *
     * @param id
     * @return
     * @throws ToDoListNotFoundException
     */
    @Override
    public ToDoListModel getToDoListByID(int id) throws ToDoListNotFoundException {
        log.info("method getToDoListByID invoked from ToDoListService");
        ToDoList toDoList = toDoListRepository.findById(id)
                .orElseThrow(() -> new ToDoListNotFoundException("toDoList not found for this id :: " + id));
        ToDoListModel toDoListModel = modelMapper.map(toDoList, ToDoListModel.class);
        return toDoListModel;
    }


    @Override
    public List<ToDoListModel> getActiveToDoListItems(){
        log.info("method getActiveToDoListItems invoked from ToDoListService");
        List<ToDoListModel> toDoListModels = (toDoListRepository
                .findAll())
                .stream()
                .map(x -> modelMapper.map(x, ToDoListModel.class))
                .filter(toDoListModel -> toDoListModel.getStatusId() == (StatusEnum.IN_PROGRESS).getId() ||
                        toDoListModel.getStatusId() == (StatusEnum.TO_DO).getId())
                .collect(Collectors.toList());
        Assert.notEmpty(toDoListModels,"No toDoList found");
        return toDoListModels;
    }


    @Override
    public List<ToDoListModel> getActiveAndOrderedToDoListItems(){
        log.info("method getActiveAndOrderedToDoListItems invoked from ToDoListService");
        List<ToDoListModel> toDoListModels = (toDoListRepository
                .findAll())
                .stream()
                .map(x -> modelMapper.map(x, ToDoListModel.class))
                .sorted(Comparator.comparing(ToDoListModel::getUserId))
                .filter(toDoListModel -> toDoListModel.getStatusId() == (StatusEnum.IN_PROGRESS).getId() ||
                        toDoListModel.getStatusId() == (StatusEnum.TO_DO).getId())
                .collect(Collectors.toList());
        Assert.notEmpty(toDoListModels,"No toDoList found");
        return toDoListModels;
    }

    /**
     *
     * @param toDoListModel
     * @return
     * @throws UserNotFoundException
     * @throws ValidationException
     */
    @Override
    public ToDoList saveToDoList(ToDoListModel toDoListModel) throws UserNotFoundException, ValidationException {
        log.info("method saveToDoList invoked from ToDoListService");
        if (toDoListModel == null)
            throw new ValidationException("toDoList not found to save");
        userRepository.findById(toDoListModel.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User specified not found"));
        ToDoList toDoList = modelMapper.map(toDoListModel, ToDoList.class);
        toDoList.setCreatedAt(LocalDateTime.now());
        toDoList.setUpdatedAt(LocalDateTime.now());
        toDoList.setDeleted(false);
        return toDoListRepository.save(toDoList);
    }


    /**
     *
     * @param id
     * @param toDoListModel
     * @return
     * @throws UserNotFoundException
     * @throws ToDoListNotFoundException
     * @throws ValidationException
     */
    @Override
    public ToDoList updateToDoListByID(int id, ToDoListModel toDoListModel) throws UserNotFoundException, ToDoListNotFoundException, ValidationException {
        log.info("method updateToDoListByID invoked from ToDoListService for id{}", id);
        if (toDoListModel != null) {
            ToDoList toDoListToBeUpdated = toDoListRepository.findById(id).orElseThrow(() ->
                    new ToDoListNotFoundException("toDoList not found for this id :: " + id));
            ToDoList toDoList = modelMapper.map(toDoListModel, ToDoList.class);
            toDoList.setUser( userRepository.findById(toDoListModel.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found")));
            toDoList.setUpdatedAt(LocalDateTime.now());
            toDoListRepository.delete(toDoListToBeUpdated);
            return toDoListRepository.save(toDoList);
        } else throw new ValidationException("Body Not Valid");
    }

    /**
     *
     * @param id
     * @throws ToDoListNotFoundException
     */
    @Override
    public void deleteToDoListByID(int id) throws ToDoListNotFoundException {
        log.info("method deleteToDoListByID invoked from ToDoListService for id{}", id);
        ToDoList toDoList = toDoListRepository.findById(id)
                .orElseThrow(() -> new ToDoListNotFoundException("toDoList not found for this id :: " + id));
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
