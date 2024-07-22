package com.myproject.todolist.Dto;

import lombok.Data;

@Data
public class TodoDto {
    private String text;
    private Boolean completed;
}
