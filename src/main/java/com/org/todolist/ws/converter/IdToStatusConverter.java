package com.org.todolist.ws.converter;

import com.org.todolist.utils.enumeration.StatusEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IdToStatusConverter implements Converter<String, StatusEnum> {
    @Override
    public StatusEnum convert(String source) {
        return StatusEnum.getById(Integer.parseInt(source));
    }
}