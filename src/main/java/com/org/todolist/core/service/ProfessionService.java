package com.org.todolist.core.service;

import com.org.todolist.infrastructure.entity.Profession;
import com.org.todolist.ws.dto.ProfessionDTO;
import com.org.todolist.ws.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfessionService {
    List<ProfessionDTO> getProfessions() throws NotFoundException;
    ProfessionDTO getProfessionByID(int id) throws NotFoundException;
    Profession saveProfession(ProfessionDTO professionDTO) throws NotFoundException;
    Profession updateProfessionByID(int id, ProfessionDTO professionDTO) throws NotFoundException;
    void deleteProfessionByID(int id) throws NotFoundException;

}
