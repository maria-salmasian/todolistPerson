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
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
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

    /**DOES NOT WORK
     *
     *
     */
   @Override
    public List<UserModel> getUsers() throws NotFoundException {
        log.info("getting all users");
        List<User>  users = (userRepository.findAll());
        List<UserModel> userModels = new ArrayList<>();

        for (int i = 0; i <users.size()-1 ; i++) {
           UserModel userModel = modelMapper.map(users.get(i), UserModel.class);
           userModel.setGenderId(users.get(i).getGender().getGender().getId());
           userModel.setProfessionId(users.get(i).getProfession().getProfession().getId());
           userModels.add(userModel);
        }

        if (!userModels.isEmpty() && userModels!= null){
            log.info("done getting all users");
            return userModels.stream().collect(Collectors.toList());
        }

        else throw new NotFoundException("No users found");


    }

    @Override
    public UserModel getUserByID(int id) throws NotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("user not found for this id :: " + id));
        log.info("getting user by id{} ", id);
        UserModel userModel = modelMapper.map(user, UserModel.class);
        userModel.setGenderId(genderRepository.findGenderById(user.getGender().getId()).getId());
        userModel.setProfessionId(professionRepository.findProfessionById(user.getGender().getId()).getId());
        return userModel;
    }


    @Override
    public User saveUser(UserModel userModel) {
        User user1 = modelMapper.map(userModel, User.class);
        Gender gender = (genderRepository.findGenderById(userModel.getGenderId()));
        Profession profession = (professionRepository.findProfessionById(userModel.getProfessionId()));
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
        Gender gender = (genderRepository.findById(userModel.getGenderId()).orElseThrow(()->new NotFoundException("gender id not found")));
        Profession profession = (professionRepository.findProfessionById(userModel.getProfessionId()));
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
