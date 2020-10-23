package com.org.todolist.ws.dto;

import com.org.todolist.utils.StatusEnum;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class StatusDTO {
    private int id; //primKey
    private StatusEnum status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
