package com.apress.todo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoDto {
    private Long id;
    @NotBlank(message = "Enter description")
//    @Min(value = 1,message = "At least one character")
//    @Max(value = 30, message = "At most 30 characters")

    private String description;
    @NotBlank(message = "Enter code")
    private String code;

    public ToDoDto(String description) {
        this.description = description;
    }
}
