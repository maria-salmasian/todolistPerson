package com.org.todolist.ws.contoller;

import com.org.todolist.core.model.UserModel;
import com.org.todolist.core.service.UserService;
import com.org.todolist.infrastructure.entity.User;
import com.org.todolist.ws.dto.UserDTO;
import com.org.todolist.ws.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;


    /**
     * @param userDTO
     * @return
     * @throws NotFoundException
     */
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) throws NotFoundException {
        UserModel createdUserModel = modelMapper.map(userDTO, UserModel.class);
        userService.saveUser(createdUserModel);
        log.info("method createUser invoked from UserController");
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    /**
     * @param id
     * @param userDTO
     * @return
     * @throws NotFoundException
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") int id,
                                              @Valid @RequestBody UserDTO userDTO) throws NotFoundException {
        UserModel updatedUserModel = modelMapper.map(userDTO, UserModel.class);
        userService.updateUserByID(id, updatedUserModel);
        log.info("method updateUser invoked from UserController");
        return new ResponseEntity<>(userDTO, HttpStatus.OK);

    }

    /**
     * @param id
     * @return
     * @throws NotFoundException
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") int id) throws NotFoundException {
        UserModel userModel = userService.getUserByID(id);
        UserDTO userDTO = modelMapper.map(userModel, UserDTO.class);
        log.info("method getUserById invoked from UserController for the user with id{}", id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }


    /**
     * @return
     * @throws NotFoundException
     */
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUser() throws NotFoundException {
        List<UserModel> userModels = userService.getUsers();
        List<UserDTO> userDTOS = userModels.stream().map(x -> modelMapper.map(x, UserDTO.class)).collect(Collectors.toList());
        log.info("method getAllUsers invoked from UserController");
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }


    /**
     * @param id
     * @return
     * @throws NotFoundException
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable("id") int id) throws NotFoundException {
        userService.deleteUserByID(id);
        log.info("method deleteUser invoked from UserController");
        return new ResponseEntity<>("Success", HttpStatus.OK);

    }

    //query paramov request
    //filtrner: /items/1?status=2
}
