package com.org.todolist.ws.contoller;

import com.org.todolist.core.model.ToDoListModel;
import com.org.todolist.core.service.ToDoListService;
import com.org.todolist.core.service.exception.ToDoListNotFoundException;
import com.org.todolist.core.service.exception.UserNotFoundException;
import com.org.todolist.core.service.exception.ValidationException;
import com.org.todolist.infrastructure.entity.ToDoList;
import com.org.todolist.utils.enumeration.StatusEnum;
import com.org.todolist.ws.dto.ToDoListDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@Slf4j
@RestController
@RequestMapping("/toDoList")
public class ToDoListController {

    @Autowired
    private ToDoListService toDoListService;
    @Autowired
    private ModelMapper modelMapper;


    @GetMapping
    public ResponseEntity<List<ToDoListDTO>> getAllActiveToDoList(@RequestParam(defaultValue = "") StatusEnum status, @RequestParam(defaultValue = "false") Boolean active, @RequestParam(defaultValue = "false") Boolean ordered) {
        log.info("method getActiveToDoLists invoked from ToDoListController, those in progress");
        if (active && ordered) {
            List<ToDoListModel> toDoListModels = toDoListService.getActiveAndOrderedToDoListItems();
            List<ToDoListDTO> toDoListDTOS = toDoListModels.stream().map(x -> modelMapper.map(x, ToDoListDTO.class)).collect(Collectors.toList());
            return new ResponseEntity<>(toDoListDTOS, HttpStatus.OK);
        } else if (active) {

            List<ToDoListModel> toDoListModels = toDoListService.getActiveToDoListItems();
            List<ToDoListDTO> toDoListDTOS = toDoListModels.stream().map(x -> modelMapper.map(x, ToDoListDTO.class)).collect(Collectors.toList());
            return new ResponseEntity<>(toDoListDTOS, HttpStatus.OK);
        } else if (ordered) {
            List<ToDoListModel> toDoListModels = toDoListService.getOrderedToDoListItems();
            List<ToDoListDTO> toDoListDTOS = toDoListModels.stream().map(x -> modelMapper.map(x, ToDoListDTO.class)).collect(Collectors.toList());
            return new ResponseEntity<>(toDoListDTOS, HttpStatus.OK);

        } else if (!status.equals("")) {
            List<ToDoListModel> toDoListModels = toDoListService.getToDoListItemsBasedOnStatus(status);
            List<ToDoListDTO> toDoListDTOS = toDoListModels.stream().map(x -> modelMapper.map(x, ToDoListDTO.class)).collect(Collectors.toList());
            return new ResponseEntity<>(toDoListDTOS, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }


    @GetMapping("/{id}")
    public ResponseEntity<ToDoListDTO> getToDoListById(@PathVariable int id) throws ToDoListNotFoundException {
        ToDoListModel toDoListModel = toDoListService.getToDoListByID(id);
        ToDoListDTO toDoListDTO = modelMapper.map(toDoListModel, ToDoListDTO.class);
        log.info("method getToDoListsById invoked from ToDoListController, with Id{}", id);
        return new ResponseEntity<>(toDoListDTO, HttpStatus.OK);

    }


    @PostMapping()
    public ResponseEntity<HttpStatus> createToDoList(@Valid @RequestBody ToDoListDTO toDoListDTO) throws ValidationException, UserNotFoundException {
        Assert.notNull(toDoListDTO, "body is null");
        ToDoListModel toDoListModel = modelMapper.map(toDoListDTO, ToDoListModel.class);
        toDoListService.saveToDoList(toDoListModel);
        log.info("method createToDoList invoked from ToDoListController");
        return new ResponseEntity<>(HttpStatus.CREATED);

    }


    @PutMapping("/{id}")
    public ResponseEntity<ToDoList> updateToDoList(@PathVariable("id") int id,
                                                   @Valid @RequestBody ToDoListDTO toDoListDTO) throws UserNotFoundException, ToDoListNotFoundException, ValidationException {
        Assert.notNull(toDoListDTO, "body is null");
        ToDoListModel toDoListModel = modelMapper.map(toDoListDTO, ToDoListModel.class);
        ToDoList updated = toDoListService.updateToDoListByID(id, toDoListModel);
        log.info("method updateToDoList invoked from ToDoListController");
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteToDoList(
            @PathVariable("id") int id) throws ToDoListNotFoundException {
        toDoListService.deleteToDoListByID(id);
        log.info("method deleteToDoList invoked from ToDoListController");
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }


    @DeleteMapping
    public ResponseEntity<String> deleteAllToDoListItems() {
        toDoListService.deleteAllToDoList();
        log.info("method deleteAllToDoListItems invoked from ToDoListController");
        return new ResponseEntity<>("Success", HttpStatus.OK);

    }
}
