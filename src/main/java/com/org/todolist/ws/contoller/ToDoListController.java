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
//bolorgetternery mi hat getteri mej filtrnerov sericeum


    @GetMapping("/getAllOrdered")
    public ResponseEntity<List<ToDoListDTO>> getOrderedToDoLists(){
        log.info("method getOrderedToDoLists invoked from ToDoListController");
        List<ToDoListModel> toDoListModels = toDoListService.getOrderedToDoListItems();
        List<ToDoListDTO> toDoListDTOS = toDoListModels.stream().map(x -> modelMapper.map(x, ToDoListDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(toDoListDTOS, HttpStatus.OK);

    }


    @GetMapping("/getAllActive")
    public ResponseEntity<List<ToDoListDTO>> getAllActiveToDoList(){
        log.info("method getActiveToDoLists invoked from ToDoListController, those in progress");
        List<ToDoListModel> toDoListModels = toDoListService.getActiveToDoListItems();
        List<ToDoListDTO> toDoListDTOS = toDoListModels.stream().map(x -> modelMapper.map(x, ToDoListDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(toDoListDTOS, HttpStatus.OK);
    }



    @GetMapping("/getByStatus")
    public ResponseEntity<List<ToDoListDTO>> getAllToDoListsByStatus(@RequestParam StatusEnum statusEnum)   {
        List<ToDoListModel> toDoListModels = toDoListService.getToDoListItemsBasedOnStatus(statusEnum);
        List<ToDoListDTO> toDoListDTOS = toDoListModels.stream().map(x -> modelMapper.map(x, ToDoListDTO.class)).collect(Collectors.toList());
        log.info("method getToDoListsByStatus invoked from ToDoListController, with status{} ", statusEnum.toString());
        return new ResponseEntity<>(toDoListDTOS, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ToDoListDTO> getToDoListById(@PathVariable int id) {
        try {
            ToDoListModel toDoListModel = toDoListService.getToDoListByID(id);
            ToDoListDTO toDoListDTO = modelMapper.map(toDoListModel, ToDoListDTO.class);
            log.info("method getToDoListsById invoked from ToDoListController, with Id{}", id);
            return new ResponseEntity<>(toDoListDTO, HttpStatus.OK);
        }
        catch (ToDoListNotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
    }


    @PostMapping()
    public ResponseEntity<HttpStatus> createToDoList(@Valid @RequestBody ToDoListDTO toDoListDTO) {
        try {
            Assert.notNull(toDoListDTO, "body is null");
            ToDoListModel toDoListModel = modelMapper.map(toDoListDTO, ToDoListModel.class);
            toDoListService.saveToDoList(toDoListModel);
            log.info("method createToDoList invoked from ToDoListController");
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (ValidationException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    @PutMapping("/{id}")
    public ResponseEntity<ToDoList> updateToDoList(@PathVariable("id") int id,
                                                   @Valid @RequestBody ToDoListDTO toDoListDTO) {
        try {
            Assert.notNull(toDoListDTO, "body is null");
            ToDoListModel toDoListModel = modelMapper.map(toDoListDTO, ToDoListModel.class);
            ToDoList updated = toDoListService.updateToDoListByID(id, toDoListModel);
            log.info("method updateToDoList invoked from ToDoListController");
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (ToDoListNotFoundException | UserNotFoundException | ValidationException e) {
            System.out.println(e.getMessage());
        }

        return null;


    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteToDoList(
            @PathVariable("id") int id) {
        try {
            toDoListService.deleteToDoListByID(id);
            log.info("method deleteToDoList invoked from ToDoListController");

            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (ToDoListNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }


    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAllToDoListItems() {
        toDoListService.deleteAllToDoList();
        log.info("method deleteAllToDoListItems invoked from ToDoListController");
        return new ResponseEntity<>("Success", HttpStatus.OK);

    }
}
