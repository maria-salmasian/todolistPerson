package com.org.todolist.ws.contoller;

import com.org.todolist.core.service.ProfessionService;
import com.org.todolist.infrastructure.entity.Profession;
import com.org.todolist.ws.dto.ProfessionDTO;
import com.org.todolist.ws.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profession")
public class ProfessionController {

    @Autowired
    ProfessionService professionService;

    @RequestMapping(method = RequestMethod.GET, value = "/getById/{id}")
    public ResponseEntity<ProfessionDTO> getProfessionById(@PathVariable("id") int id) throws NotFoundException {
        ProfessionDTO userDTO = professionService.getProfessionByID(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    public ResponseEntity<List<ProfessionDTO>> getAllProfessions() throws NotFoundException {
        List<ProfessionDTO> users = professionService.getProfessions();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<HttpStatus> createProfession(@RequestBody ProfessionDTO professionDTO) throws NotFoundException {
        professionService.saveProfession(professionDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/update/{id}")
    public ResponseEntity<HttpStatus> updateProfession(@PathVariable("id") int id,
                                                       @RequestBody ProfessionDTO professionDTO) throws NotFoundException {
        professionService.updateProfessionByID(id, professionDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(
            @PathVariable("id") int id) throws NotFoundException {
        professionService.deleteProfessionByID(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
