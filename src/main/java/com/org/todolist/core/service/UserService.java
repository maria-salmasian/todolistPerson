package com.org.todolist.core.service;

import com.org.todolist.core.model.UserModel;
import com.org.todolist.core.service.exception.NotFoundException;
import com.org.todolist.core.service.exception.UserNotFoundException;
import com.org.todolist.core.service.exception.ValidationException;
import com.org.todolist.infrastructure.entity.User;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserModel> getUsers();
    UserModel getUserByID(int id) throws UserNotFoundException;
    User saveUser(UserModel userModel) throws ValidationException;
    User updateUserByID(int id, UserModel userModel) throws NotFoundException;
    void deleteUserByID(int id) throws  UserNotFoundException;

//    public List<UserModel> getUsersByFilter( String searchString, FilterRequest filter);

}
