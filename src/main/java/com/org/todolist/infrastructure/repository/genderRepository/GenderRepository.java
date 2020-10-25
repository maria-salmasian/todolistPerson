package com.org.todolist.infrastructure.repository.genderRepository;

import com.org.todolist.infrastructure.entity.Gender;
import com.org.todolist.utils.GenderEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Integer> {

    Gender findByGender(GenderEnum genderEnum);
}
