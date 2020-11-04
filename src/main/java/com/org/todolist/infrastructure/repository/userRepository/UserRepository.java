package com.org.todolist.infrastructure.repository.userRepository;

import com.org.todolist.infrastructure.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Override
    Optional<User> findById(Integer integer);

    @Override
    <S extends User> Optional<S> findOne(Example<S> example);
    
}

