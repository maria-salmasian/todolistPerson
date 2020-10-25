package com.org.todolist.infrastructure.repository.statusRepository;

import com.org.todolist.infrastructure.entity.Status;
import com.org.todolist.utils.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
    Status findByStatus(StatusEnum statusEnum);
}
