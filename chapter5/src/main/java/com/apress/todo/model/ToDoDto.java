package com.apress.todo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoDto {
    private String id;
    private String description;

    public ToDoDto(String description) {
        this.description = description;
    }
}
