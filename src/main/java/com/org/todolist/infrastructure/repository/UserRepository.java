package com.org.todolist.infrastructure.repository;

import com.org.todolist.infrastructure.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>  {
    @Override
    Optional<User> findById(Integer integer);

    User findUserById(Integer id);

    @Override
    <S extends User> Optional<S> findOne(Example<S> example);

}

