package com.org.todolist.infrastructure.repository.professionRepository;

import com.org.todolist.infrastructure.entity.Profession;
import com.org.todolist.utils.ProfessionEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionRepository extends JpaRepository<Profession, Integer> {

    Profession findByProfession(ProfessionEnum professionEnum);
}
