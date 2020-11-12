package com.org.todolist.core.service.impl;

import com.org.todolist.core.model.UserModel;
import com.org.todolist.core.service.UserService;
import com.org.todolist.core.service.exception.NotFoundException;
import com.org.todolist.core.service.exception.UserNotFoundException;
import com.org.todolist.core.service.exception.ValidationException;
import com.org.todolist.infrastructure.entity.Gender;
import com.org.todolist.infrastructure.entity.Profession;
import com.org.todolist.infrastructure.entity.User;
import com.org.todolist.infrastructure.repository.GenderRepository;
import com.org.todolist.infrastructure.repository.ProfessionRepository;
import com.org.todolist.infrastructure.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.Assert;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private ProfessionRepository professionRepository;

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

    @Override
    public UserModel getUserByID(int id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user not found for this id :: " + id));
        log.info("getting user by id{} ", id);
        UserModel userModel = modelMapper.map(user, UserModel.class);
        userModel.setGenderId(genderRepository.findGenderById(user.getGender().getId()).getId());
        userModel.setProfessionId(professionRepository.findProfessionById(user.getGender().getId()).getId());
        return userModel;
    }


    @Override
    public User saveUser(UserModel userModel) throws ValidationException {
        if (userModel != null) {
            User user = modelMapper.map(userModel, User.class);
            Gender gender = (genderRepository.findGenderById(userModel.getGenderId()));
            Profession profession = (professionRepository.findProfessionById(userModel.getProfessionId()));
            user.setGender(gender);
            user.setProfession(profession);
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
            user.setDeleted(false);
            log.info("saving user", user.getId());
            return userRepository.save(user);
        }
        else throw new ValidationException("Body Not Valid");

    }

    @Override
    public User updateUserByID(int id, UserModel userModel) throws NotFoundException {
        User userToBeUpdated = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("user not found for this id :: " + id));
        Assert.notNull(userModel, "the body is null");
        User user1 = modelMapper.map(userModel, User.class);
        Gender gender = (genderRepository.findById(userModel.getGenderId()).orElseThrow(() -> new NotFoundException("gender id not found")));
        Profession profession = (professionRepository.findProfessionById(userModel.getProfessionId()));
        user1.setGender(gender);
        user1.setProfession(profession);
        user1.setUpdatedAt(LocalDateTime.now());
        user1.setDeleted(false);
        log.info("updating user", user1.getId());
        userRepository.delete(userToBeUpdated);
        return userRepository.save(user1);
    }

    @Override
    public void deleteUserByID(int id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user not found for this id :: " + id));
        user.setDeleted(true);
        log.info("deleting user", user.getId());
        userRepository.save(user);

    }
}
