package com.org.todolist.core.service;

import com.org.todolist.infrastructure.entity.User;
import com.org.todolist.ws.dto.UserDTO;
import com.org.todolist.ws.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserDTO> getUsers() throws NotFoundException;
    UserDTO getUserByID(int id) throws NotFoundException;
    User saveUser(UserDTO user) throws NotFoundException;
    User updateUserByID(int id, UserDTO user) throws NotFoundException;
    void deleteUserByID(int id) throws NotFoundException;

}
