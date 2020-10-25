package com.org.todolist.core.service.impl;

import com.org.todolist.core.service.UserService;
import com.org.todolist.infrastructure.entity.Gender;
import com.org.todolist.infrastructure.entity.Profession;
import com.org.todolist.infrastructure.entity.User;
import com.org.todolist.infrastructure.repository.genderRepository.GenderRepository;
import com.org.todolist.infrastructure.repository.professionRepository.ProfessionRepository;
import com.org.todolist.infrastructure.repository.userRepository.UserRepository;
import com.org.todolist.ws.dto.UserDTO;
import com.org.todolist.ws.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GenderRepository genderRepository;

    @Autowired
    ProfessionRepository professionRepository;

    private UserDTO fromEntityToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setDeleted(user.isDeleted());
        userDTO.setAge(user.getAge());
        userDTO.setSalary(user.getSalary());
        userDTO.setUpdatedAt(user.getUpdatedAt());
        return userDTO;
    }

    private User fromDTOToEntity(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setCreatedAt(userDTO.getCreatedAt());
        user.setDeleted(userDTO.isDeleted());
        user.setAge(userDTO.getAge());
        user.setSalary(userDTO.getSalary());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return user;
    }

    @Override
    public List<UserDTO> getUsers() throws NotFoundException {
        List<UserDTO> userList = ((List<User>) userRepository
                .findAll())
                .stream()
                .map(this::fromEntityToDTO)
                .collect(Collectors.toList());

        if (!userList.isEmpty())
            return userList;
        else throw new NotFoundException("No users found");
    }

    @Override
    public UserDTO getUserByID(int id) throws NotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("user not found for this id :: " + id));
        return fromEntityToDTO(user);
    }

    @Override
    public User saveUser(UserDTO user) {
        Gender gender = genderRepository.findByGender(user.getGender());
        if (gender == null) {
            gender = genderRepository.save(new Gender(user.getGender(), LocalDateTime.now(), LocalDateTime.now()));
        }

        Profession profession = professionRepository.findByProfession(user.getProfession());

        if (profession == null) {
            profession = professionRepository.save(new Profession(user.getProfession(), LocalDateTime.now(), LocalDateTime.now(), false));
        }

        User user1 = fromDTOToEntity(user);
        gender.getUsers().add(user1);
        profession.getUsers().add(user1);
        user1.setGender(gender);
        user1.setProfession(profession);
        return userRepository.save(user1);
    }

    @Override
    public User updateUserByID(int id, UserDTO userDTO) throws NotFoundException {
        User user1 = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("user not found for this id :: " + id));
        Gender gender = genderRepository.findByGender(userDTO.getGender());
        if (gender == null) {
            gender = genderRepository.save(new Gender(userDTO.getGender(), LocalDateTime.now(), LocalDateTime.now()));
        }

        Profession profession = professionRepository.findByProfession(userDTO.getProfession());

        if (profession == null) {
            profession = professionRepository.save(new Profession(userDTO.getProfession(), LocalDateTime.now(), LocalDateTime.now(), false));
        }

        gender.getUsers().add(user1);
        profession.getUsers().add(user1);
        user1.setName(userDTO.getName());
        user1.setSurname(userDTO.getSurname());
        user1.setAge(userDTO.getAge());
        user1.setUpdatedAt(LocalDateTime.now());
        user1.setDeleted(false);
        user1.setGender(gender);
        user1.setProfession(profession);
        user1.setSalary(userDTO.getSalary());
        return userRepository.save(user1);
    }

    @Override
    public void deleteUserByID(int id) throws NotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("user not found for this id :: " + id));
        user.setDeleted(true);
        userRepository.save(user);

    }
}
