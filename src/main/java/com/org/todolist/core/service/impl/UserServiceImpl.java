package com.org.todolist.core.service.impl;

import com.org.todolist.core.model.UserModel;
import com.org.todolist.core.service.UserService;
import com.org.todolist.core.service.exception.NotFoundException;
import com.org.todolist.core.service.exception.UserNotFoundException;
import com.org.todolist.core.service.exception.ValidationException;
import com.org.todolist.infrastructure.entity.User;
import com.org.todolist.infrastructure.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private UserSpecification userSpecification;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserModel> getUsers() {
        log.info("getting all users");
        List<UserModel> userList = (userRepository.findAll())
                .stream()
                .map(x -> modelMapper.map(x, UserModel.class))
                .collect(Collectors.toList());
        Assert.notEmpty(userList, "No user found");
        return userList;
    }


//    @Override
//    public List<UserModel> getUsersByFilter( String searchString, FilterRequest filter) {
//
//        List<UserModel> userList= (List<UserModel>) userRepository.findAll(Specification.where(userSpecification.hasString(searchString)
//                .or(userSpecification.hasGender(searchString)))
//                .and(userSpecification.getFilter(filter)))
//                .stream()
//                .map(x -> modelMapper.map(x, UserModel.class))
//                .collect(Collectors.toList());
//
//        return userList;
//    }


    /**
     *
     * @param id
     * @return
     * @throws UserNotFoundException
     */
    @Override
    public UserModel getUserByID(int id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user not found for this id :: " + id));
        log.info("getting user by id{} ", id);
        UserModel userModel = modelMapper.map(user, UserModel.class);
        return userModel;
    }


    /**
     *
     * @param userModel
     * @return
     * @throws ValidationException
     */
    @Override
    public User saveUser(UserModel userModel) throws ValidationException {
        if (userModel != null) {
            User user = modelMapper.map(userModel, User.class);
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
            user.setDeleted(false);
            log.info("saving user", user.getId());
            return userRepository.save(user);
        } else throw new ValidationException("Body Not Valid");

    }

    /**
     *
     * @param id
     * @param userModel
     * @return
     * @throws NotFoundException
     */
    @Override
    public User updateUserByID(int id, UserModel userModel) throws UserNotFoundException {
        User userToBeUpdated = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("user not found for this id :: " + id));
        Assert.notNull(userModel, "the body is null");
        User user1 = modelMapper.map(userModel, User.class);
        user1.setUpdatedAt(LocalDateTime.now());
        user1.setDeleted(false);
        log.info("updating user", user1.getId());
        userRepository.delete(userToBeUpdated);
        return userRepository.save(user1);
    }

    /**
     *
     * @param id
     * @throws UserNotFoundException
     */
    @Override
    public void deleteUserByID(int id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user not found for this id :: " + id));
        user.setDeleted(true);
        log.info("deleting user", user.getId());
        userRepository.save(user);

    }
}
