package com.org.todolist.ws.contoller;

import com.org.todolist.core.service.UserService;
import com.org.todolist.infrastructure.entity.User;
import com.org.todolist.ws.dto.UserDTO;
import com.org.todolist.ws.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<User> createUser(@RequestBody UserDTO user) throws NotFoundException {
        User created = userService.saveUser(user);
        return new ResponseEntity<>(created, HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id")  int id,
                                           @RequestBody  UserDTO userDTO) throws NotFoundException{
        User updated = userService.updateUserByID(id, userDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/getById/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id")  int id) throws NotFoundException {
        UserDTO userDTO = userService.getUserByID(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    public ResponseEntity<List<UserDTO>> getAllUser() throws NotFoundException {
        List<UserDTO> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/delete/{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable("id")  int id) throws NotFoundException {
        userService.deleteUserByID(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);

    }
}
