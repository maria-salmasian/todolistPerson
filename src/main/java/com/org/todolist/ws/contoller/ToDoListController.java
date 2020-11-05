package com.org.todolist.ws.contoller;

import com.org.todolist.core.model.ToDoListModel;
import com.org.todolist.core.service.ToDoListService;
import com.org.todolist.core.service.impl.ToDoListServiceImpl;
import com.org.todolist.infrastructure.entity.ToDoList;
import com.org.todolist.utils.enumeration.StatusEnum;
import com.org.todolist.ws.dto.ToDoListDTO;
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
@RequestMapping("/toDoList")
public class ToDoListController {

    @Autowired
    private ToDoListService toDoListService;
    @Autowired
    private ModelMapper modelMapper;


    /**
     * @return
     * @throws NotFoundException
     */
    @GetMapping("/getAllOrdered")
    public ResponseEntity<List<ToDoListDTO>> getOrderedToDoLists() throws NotFoundException {
        List<ToDoListModel> toDoListModels = toDoListService.getOrderedToDoListItems();
        List<ToDoListDTO> toDoListDTOS = toDoListModels.stream().map(x -> modelMapper.map(x, ToDoListDTO.class)).collect(Collectors.toList());
        log.info("method getOrderedToDoLists invoked from ToDoListController");
        return new ResponseEntity<>(toDoListDTOS, HttpStatus.OK);

    }

    /**
     * @return
     * @throws NotFoundException
     */
    @GetMapping("/getAllActive")
    public ResponseEntity<List<ToDoListDTO>> getAllActiveToDoList() throws NotFoundException {
        List<ToDoListModel> toDoListModels = toDoListService.getActiveToDoListItems();
        List<ToDoListDTO> toDoListDTOS = toDoListModels.stream().map(x -> modelMapper.map(x, ToDoListDTO.class)).collect(Collectors.toList());
        log.info("method getActiveToDoLists invoked from ToDoListController, those in progress");
        return new ResponseEntity<>(toDoListDTOS, HttpStatus.OK);
    }


    /**
     * @param statusId
     * @return
     * @throws NotFoundException
     */
    @GetMapping("/getByStatus")
    public ResponseEntity<List<ToDoListDTO>> getAllToDoListsByStatus(@RequestParam int statusId) throws NotFoundException {
        List<ToDoListModel> toDoListModels = toDoListService.getToDoListItemsBasedOnStatus(statusId);
        List<ToDoListDTO> toDoListDTOS = toDoListModels.stream().map(x -> modelMapper.map(x, ToDoListDTO.class)).collect(Collectors.toList());
        log.info("method getToDoListsByStatus invoked from ToDoListController, with status{}", StatusEnum.getById(statusId));
        return new ResponseEntity<>(toDoListDTOS, HttpStatus.OK);
    }

    /**
     * @param id
     * @return
     * @throws NotFoundException
     */
    @GetMapping("/{id}")
    public ResponseEntity<ToDoListDTO> getToDoListById(@PathVariable int id) throws NotFoundException {
        ToDoListModel toDoListModel = toDoListService.getToDoListByID(id);
        ToDoListDTO toDoListDTOS = modelMapper.map(toDoListModel, ToDoListDTO.class);
        log.info("method getToDoListsById invoked from ToDoListController, with Id{}", id);
        return new ResponseEntity<>(toDoListDTOS, HttpStatus.OK);
    }


    /**
     * @param toDoListDTO
     * @return
     * @throws NotFoundException
     */
    @PostMapping()
    public ResponseEntity<HttpStatus> createToDoList(@Valid @RequestBody ToDoListDTO toDoListDTO) throws NotFoundException {
        ToDoListModel toDoListModel = modelMapper.map(toDoListDTO, ToDoListModel.class);
        toDoListModel.setUserId(toDoListDTO.getUserId());
        toDoListDTO.setStatusId(toDoListDTO.getStatusId());
        toDoListService.saveToDoList(toDoListModel);
        log.info("method createToDoList invoked from ToDoListController");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * @param id
     * @param toDoListDTO
     * @return
     * @throws NotFoundException
     */
    @PutMapping("/{id}")
    public ResponseEntity<ToDoList> updateToDoList(@PathVariable("id") int id,
                                                   @Valid @RequestBody ToDoListDTO toDoListDTO) throws NotFoundException {
        ToDoListModel toDoListModel = modelMapper.map(toDoListDTO, ToDoListModel.class);
        ToDoList updated = toDoListService.updateToDoListByID(id, toDoListModel);
        log.info("method updateToDoList invoked from ToDoListController");
        return new ResponseEntity<>(updated, HttpStatus.OK);

    }

    /**
     * @param id
     * @return
     * @throws NotFoundException
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteToDoList(
            @PathVariable("id") int id) throws NotFoundException {
        toDoListService.deleteToDoListByID(id);
        log.info("method deleteToDoList invoked from ToDoListController");

        return new ResponseEntity<>("Success", HttpStatus.OK);

    }

    /**
     * @return
     * @throws NotFoundException
     */
    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAllToDoListItems() throws NotFoundException {
        toDoListService.deleteAllToDoList();
        log.info("method deleteAllToDoListItems invoked from ToDoListController");
        return new ResponseEntity<>("Success", HttpStatus.OK);

    }
}
