package com.org.todolist.ws.contoller;

import com.org.todolist.core.service.impl.ToDoListServiceImpl;
import com.org.todolist.infrastructure.entity.ToDoList;
import com.org.todolist.utils.StatusEnum;
import com.org.todolist.ws.dto.ToDoListDTO;
import com.org.todolist.ws.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/toDoList")
public class ToDoListController {

    @Autowired
    ToDoListServiceImpl toDoListService;


    @RequestMapping(method = RequestMethod.GET, value = "/getAllOrdered")
    public ResponseEntity<List<ToDoListDTO>> getOrderedToDoLists() throws NotFoundException {
        List<ToDoListDTO> toDoListDTOS = toDoListService.getOrderedToDoListItems();
        return new ResponseEntity<>(toDoListDTOS, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllActive")
    public ResponseEntity<List<ToDoListDTO>> getAllActiveToDoList() throws NotFoundException {
        List<ToDoListDTO> toDoListDTOS = toDoListService.getActiveToDoListItems();
        return new ResponseEntity<>(toDoListDTOS, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getByStatus")
    public ResponseEntity<List<ToDoListDTO>> getAllToDoListsByStatus(@RequestParam StatusEnum status) throws NotFoundException {
        List<ToDoListDTO> toDoListDTOS = toDoListService.getToDoListItemsBasedOnStatus(status);
        return new ResponseEntity<>(toDoListDTOS, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getById")
    public ResponseEntity<ToDoListDTO> getToDoListById(@PathVariable int id) throws NotFoundException {
        ToDoListDTO toDoListDTOS = toDoListService.getToDoListByID(id);
        return new ResponseEntity<>(toDoListDTOS, HttpStatus.OK);
    }



    @PostMapping()
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<HttpStatus> createToDoList(@RequestBody ToDoListDTO toDoList) throws NotFoundException {
        toDoListService.saveToDoList(toDoList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @RequestMapping(method = RequestMethod.PUT, value = "/toDoList")
    public ResponseEntity<ToDoList> updateToDoList(@PathVariable("id")  int id,
                                           @RequestBody  ToDoListDTO toDoList) throws NotFoundException{
        ToDoList updated = toDoListService.updateToDoListByID(id, toDoList);
        return new ResponseEntity<>(updated, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public ResponseEntity<String> deleteToDoList(
            @PathVariable("id")  int id) throws NotFoundException {
        toDoListService.deleteToDoListByID(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteAll")
    public ResponseEntity<String> deleteAllToDoListItems() throws NotFoundException {
        toDoListService.deleteAllToDoList();
        return new ResponseEntity<>("Success", HttpStatus.OK);

    }
}
