package com.org.todolist.infrastructure.repository.professionRepository;

import com.org.todolist.infrastructure.entity.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ProfessionRepository extends JpaRepository<Profession, Integer> {
}
