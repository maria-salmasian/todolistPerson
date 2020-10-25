package com.org.todolist.core.service.impl;

import com.org.todolist.core.service.ToDoListService;
import com.org.todolist.infrastructure.entity.Status;
import com.org.todolist.infrastructure.entity.ToDoList;
import com.org.todolist.infrastructure.entity.User;
import com.org.todolist.infrastructure.repository.statusRepository.StatusRepository;
import com.org.todolist.infrastructure.repository.toDoListRepository.ToDoListRepository;
import com.org.todolist.infrastructure.repository.userRepository.UserRepository;
import com.org.todolist.utils.StatusEnum;
import com.org.todolist.ws.dto.ToDoListDTO;
import com.org.todolist.ws.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToDoListServiceImpl implements ToDoListService {

    @Autowired
    ToDoListRepository toDoListRepository;
    @Autowired
    StatusRepository statusRepository;

    @Autowired
    UserRepository userRepository;

    private ToDoListDTO convertToToDoListDTO(ToDoList toDoList) {
        ToDoListDTO toDoListDTO = new ToDoListDTO();
        toDoListDTO.setCreatedAt(toDoList.getCreatedAt());
        toDoListDTO.setDeleted(toDoList.isDeleted());
        toDoListDTO.setUpdatedAt(toDoList.getUpdatedAt());
        toDoListDTO.setUserId(toDoList.getUser().getId());
        toDoListDTO.setDescription(toDoList.getDescription());
        toDoListDTO.setStatus(toDoList.getStatus().getStatus());
        return toDoListDTO;
    }

    private ToDoList fromToDoListDtoToEntity(ToDoListDTO doListDTO) {
        User user = userRepository.findById(doListDTO.getUserId()).orElse(null);
        Status status = statusRepository.findByStatus(doListDTO.getStatus());
        ToDoList toDoList = new ToDoList();
        toDoList.setCreatedAt(doListDTO.getCreatedAt());
        toDoList.setDeleted(doListDTO.isDeleted());
        toDoList.setUpdatedAt(doListDTO.getUpdatedAt());
        toDoList.setUser(user);
        toDoList.setDescription(doListDTO.getDescription());
        toDoList.setStatus(status);
        return toDoList;
    }

    @Override
    public List<ToDoListDTO> getOrderedToDoListItems() throws NotFoundException {
        List<ToDoListDTO> userList = (toDoListRepository
                .findAll())
                .stream()
                .map(this::convertToToDoListDTO)
                .sorted(Comparator.comparing(ToDoListDTO::getUserId))
                .collect(Collectors.toList());

        if (!userList.isEmpty())
            return userList;
        else throw new NotFoundException("No toDoList found");
    }


    @Override
    public List<ToDoListDTO> getToDoListItemsBasedOnStatus(StatusEnum status) throws NotFoundException {
        List<ToDoListDTO> userList = (toDoListRepository
                .findAllByStatus(status))
                .stream()
                .map(this::convertToToDoListDTO)
                .collect(Collectors.toList());
        if (!userList.isEmpty())
            return userList;
        else throw new NotFoundException("No toDoList found");
    }


    @Override
    public ToDoListDTO getToDoListByID(int id) throws NotFoundException {
        ToDoList toDoList = toDoListRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("toDoList not found for this id :: " + id));
        return convertToToDoListDTO(toDoList);
    }

    @Override
    public List<ToDoListDTO> getActiveToDoListItems() throws NotFoundException {
        List<ToDoListDTO> userList = (toDoListRepository
                .findAll())
                .stream()
                .map(this::convertToToDoListDTO).filter(toDoListDTO -> toDoListDTO.getStatus().equals(StatusEnum.IN_PROGRESS))
                .collect(Collectors.toList());
        if (!userList.isEmpty())
            return userList;
        else throw new NotFoundException("No toDoList found");

    }

    @Override
    public void saveToDoList(ToDoListDTO toDoListDto) throws NotFoundException {
        if (toDoListDto == null)
            throw new NotFoundException("toDoList not found to save");
        ToDoList toDoList = new ToDoList();
        User user = userRepository.findById(toDoListDto.getUserId())
                .orElseThrow(() -> new NotFoundException("User specified not found"));
        Status status = statusRepository.findByStatus(toDoListDto.getStatus());
        if (status == null) {
            status = statusRepository.save(new Status(toDoListDto.getStatus(), LocalDateTime.now(), LocalDateTime.now()));
        }

        toDoList = fromToDoListDtoToEntity(toDoListDto);
        toDoList.setCreatedAt(LocalDateTime.now());
        toDoList.setUpdatedAt(LocalDateTime.now());
        toDoList.setDescription(toDoListDto.getDescription());
        toDoList.setDeleted(false);
        status.getToDoLists().add(toDoList);
        toDoListRepository.save(toDoList);
    }

    @Override
    public ToDoList updateToDoListByID(int id, ToDoListDTO toDoListDTO) throws NotFoundException {
        ToDoList toDoList = toDoListRepository.findById(id).orElseThrow(() ->
                new NotFoundException("toDoList not found for this id :: " + id));
        User user = userRepository.findById(toDoListDTO.getUserId()).orElseThrow(() -> new NotFoundException("User not found"));
        Status status = statusRepository.findByStatus(toDoListDTO.getStatus());
        if (status == null) {
            status = statusRepository.save(new Status(toDoListDTO.getStatus(), LocalDateTime.now(), LocalDateTime.now()));
        }
        toDoList.setUpdatedAt(LocalDateTime.now());
        toDoList.setDescription(toDoListDTO.getDescription());
        toDoList.setStatus(status);
        toDoList.setUser(user);
        return toDoListRepository.save(toDoList);
    }

    @Override
    public void deleteToDoListByID(int id) throws NotFoundException {
        ToDoList toDoList = toDoListRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("toDoList not found for this id :: " + id));
        toDoList.setDeleted(true);
        toDoListRepository.save(toDoList);
    }

    @Override
    public void deleteAllToDoList() {
        List<ToDoList> all = toDoListRepository.findAll();
        all.stream().map(item -> {
            item.setDeleted(true);
            toDoListRepository.save(item);
            return null;
        });
    }


}
