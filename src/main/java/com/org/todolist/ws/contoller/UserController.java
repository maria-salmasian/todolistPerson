package com.org.todolist.ws.contoller;

import com.org.todolist.core.model.UserModel;
import com.org.todolist.core.service.UserService;
import com.org.todolist.core.service.exception.NotFoundException;
import com.org.todolist.core.service.exception.UserNotFoundException;
import com.org.todolist.core.service.exception.ValidationException;
import com.org.todolist.ws.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        try {
            Assert.notNull(userDTO, "body is null");
            UserModel createdUserModel = modelMapper.map(userDTO, UserModel.class);
            userService.saveUser(createdUserModel);
            log.info("method createUser invoked from UserController");
            return new ResponseEntity<>(userDTO, HttpStatus.CREATED);

        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") int id,
                                              @Valid @RequestBody UserDTO userDTO) {
        try {
            Assert.notNull(userDTO, "body is null");
            UserModel updatedUserModel = modelMapper.map(userDTO, UserModel.class);
            userService.updateUserByID(id, updatedUserModel);
            log.info("method updateUser invoked from UserController");
            return new ResponseEntity<>(userDTO, HttpStatus.OK);

        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") int id) {
        try {
            UserModel userModel = userService.getUserByID(id);
            UserDTO userDTO = modelMapper.map(userModel, UserDTO.class);
            log.info("method getUserById invoked from UserController for the user with id{}", id);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUser() {
        List<UserModel> toDoListModels = userService.getUsers();
        List<UserDTO> toDoListDTOS = toDoListModels.stream().map(x -> modelMapper.map(x, UserDTO.class)).collect(Collectors.toList());
        log.info("method getOrderedToDoLists invoked from ToDoListController");
        return new ResponseEntity<>(toDoListDTOS, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable("id") int id) {
        try {
            userService.deleteUserByID(id);
            log.info("method deleteUser invoked from UserController");
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
