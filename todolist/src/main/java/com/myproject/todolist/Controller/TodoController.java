package com.myproject.todolist.Controller;

import com.myproject.todolist.Dto.TodoDto;
import com.myproject.todolist.Service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/todos")
    public TodoDto addTodo(@RequestBody TodoDto todoDto){
        return todoService.addTodo(todoDto);
    }
    @PatchMapping("/todos/{id}")
    public ResponseEntity<String> toggleComplete(@RequestBody TodoDto todoDto){
        return todoService.toggleComplete(todoDto);
    }
    @DeleteMapping("/todos/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id){
        return todoService.deleteTodo(id);
    }
}
