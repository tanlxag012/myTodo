package com.myproject.todolist.Controller;

import com.myproject.todolist.Dto.TodoDto;
import com.myproject.todolist.Service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoController {
    @Autowired
    TodoService todoService;
    @GetMapping("/todos")
    public List<TodoDto> todoDtoList(){
        return todoService.getTodos();
    }
}
