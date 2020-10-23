package com.org.todolist.ws.dto;

import com.org.todolist.utils.ProfessionEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProfessionDTO {
    private int id; //primKey
    private ProfessionEnum profession;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
