package com.org.todolist.core.service;

import com.org.todolist.core.model.UserModel;
import com.org.todolist.infrastructure.entity.User;
import com.org.todolist.ws.dto.UserDTO;
import com.org.todolist.ws.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserModel> getUsers() throws NotFoundException;
    UserModel getUserByID(int id) throws NotFoundException;
    User saveUser(UserModel userModel) throws NotFoundException;
    User updateUserByID(int id, UserModel userModel) throws NotFoundException;
    void deleteUserByID(int id) throws NotFoundException;

}
