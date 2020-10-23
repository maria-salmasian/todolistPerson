package com.org.todolist.infrastructure.repository.statusRepository;

import com.org.todolist.infrastructure.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface StatusRepository extends JpaRepository<Status, Integer> {
}
