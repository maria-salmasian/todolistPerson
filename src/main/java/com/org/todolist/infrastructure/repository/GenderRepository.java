package com.org.todolist.infrastructure.repository;

import com.org.todolist.infrastructure.entity.Gender;
import com.org.todolist.infrastructure.entity.User;
import com.org.todolist.utils.enumeration.GenderEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Integer> {
    Gender findGenderById(Integer id);

    @Override
    Optional<Gender> findById(Integer integer);

//    List<Gender> findAllByGenderIdContainsIgnoreCase(int gender_id);
    Gender findByGender(GenderEnum genderEnum);
}
