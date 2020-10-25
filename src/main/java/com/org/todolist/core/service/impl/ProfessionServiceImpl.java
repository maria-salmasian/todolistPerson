package com.org.todolist.core.service.impl;


import com.org.todolist.core.service.ProfessionService;
import com.org.todolist.infrastructure.entity.Profession;
import com.org.todolist.infrastructure.repository.professionRepository.ProfessionRepository;
import com.org.todolist.ws.dto.ProfessionDTO;
import com.org.todolist.ws.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessionServiceImpl implements ProfessionService {

    @Autowired
    ProfessionRepository professionRepository;

    private ProfessionDTO convertToProfessionDTO(Profession profession) {
        ProfessionDTO professionDTO = new ProfessionDTO();
        professionDTO.setCreatedAt(profession.getCreatedAt());
        professionDTO.setProfession(profession.getProfession());
        professionDTO.setUpdatedAt(profession.getUpdatedAt());
        return professionDTO;
    }

    private Profession fromDtoToEntity(ProfessionDTO professionDTO) {

        Profession profession = new Profession();
        profession.setCreatedAt(LocalDateTime.now());
        profession.setProfession(profession.getProfession());
        profession.setUpdatedAt(LocalDateTime.now());
        return profession;

    }

    @Override

    public List<ProfessionDTO> getProfessions() throws NotFoundException {
        List<ProfessionDTO> professionDTOSList = (professionRepository
                .findAll())
                .stream()
                .map(this::convertToProfessionDTO)
                .collect(Collectors.toList());

        if (!professionDTOSList.isEmpty())
            return professionDTOSList;
        else throw new NotFoundException("No professions found");
    }


    @Override
    public ProfessionDTO getProfessionByID(int id) throws NotFoundException {
        Profession profession = professionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("profession not found for this id :: " + id));
        return convertToProfessionDTO(profession);
    }

    @Override
    public Profession saveProfession(ProfessionDTO professionDTO) throws NotFoundException {

        if (professionDTO != null)
            return professionRepository.save((fromDtoToEntity(professionDTO)));
        else throw new NotFoundException("profession not found to save");
    }

    @Override
    public Profession updateProfessionByID(int id, ProfessionDTO professionDTO) throws NotFoundException {
        Profession profession = professionRepository.findById(id).orElseThrow(() ->
                new NotFoundException("profession not found for this id :: " + id));

        profession.setDeleted(false);
        profession.setProfession(professionDTO.getProfession());

        return professionRepository.save(profession);
    }

    @Override
    public void deleteProfessionByID(int id) throws NotFoundException {
        Profession profession = professionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("user not found for this id :: " + id));
        profession.setDeleted(true);
        professionRepository.save(profession);
    }
}
