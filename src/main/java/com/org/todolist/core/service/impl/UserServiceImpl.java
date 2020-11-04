package com.org.todolist.core.service.impl;

import com.org.todolist.core.model.UserModel;
import com.org.todolist.core.service.UserService;
import com.org.todolist.infrastructure.entity.Gender;
import com.org.todolist.infrastructure.entity.Profession;
import com.org.todolist.infrastructure.entity.User;
import com.org.todolist.infrastructure.repository.genderRepository.GenderRepository;
import com.org.todolist.infrastructure.repository.professionRepository.ProfessionRepository;
import com.org.todolist.infrastructure.repository.userRepository.UserRepository;
import com.org.todolist.ws.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    GenderRepository genderRepository;

    @Autowired
    ProfessionRepository professionRepository;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public List<UserModel> getUsers() throws NotFoundException {
        log.info("getting all users");
        List<UserModel> userModels = (userRepository
                .findAll())
                .stream()
                .map(x-> modelMapper.map(x, UserModel.class))
                .collect(Collectors.toList());

        if (!userModels.isEmpty())
            return userModels;
        else throw new NotFoundException("No users found ok");
    }

    @Override
    public UserModel getUserByID(int id) throws NotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("user not found for this id :: " + id));
        log.info("getting user by id{}", id);
        UserModel userModel = modelMapper.map(user, UserModel.class);
        return userModel;
    }


    @Override
    public User saveUser(UserModel userModel) {
        User user1 = modelMapper.map(userModel, User.class);
        Gender gender = (genderRepository.findGenderById(userModel.getGender()));
        Profession profession = (professionRepository.findProfessionById(userModel.getProfession()));
        user1.setGender(gender);
        user1.setProfession(profession);
        user1.setCreatedAt(LocalDateTime.now());
        user1.setUpdatedAt(LocalDateTime.now());
        user1.setDeleted(false);
        log.info("saving user", user1.getId());
        return userRepository.save(user1);
    }

    @Override
    public User updateUserByID(int id, UserModel userModel) throws NotFoundException {
         userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("user not found for this id :: " + id));
        User user1 = modelMapper.map(userModel, User.class);
        Gender gender = (genderRepository.findGenderById(userModel.getGender()));
        Profession profession = (professionRepository.findProfessionById(userModel.getProfession()));
        user1.setGender(gender);
        user1.setProfession(profession);
        user1.setUpdatedAt(LocalDateTime.now());
        user1.setDeleted(false);
        log.info("updating user", user1.getId());
        return userRepository.save(user1);
    }

    @Override
    public void deleteUserByID(int id) throws NotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("user not found for this id :: " + id));
        user.setDeleted(true);
        log.info("deleting user", user.getId());
        userRepository.save(user);

    }
}
