package com.myproject.todolist.Service;

import com.myproject.todolist.Dto.TodoDto;
import com.myproject.todolist.Models.Todo;
import com.myproject.todolist.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository;

    public List<TodoDto> getTodos() {
       List<Todo> todos =  todoRepository.findAll();
       return todos.stream().map(todo -> {
           TodoDto todoDto = new TodoDto();
           todoDto.setText(todo.getText());
           todoDto.setCompleted(todo.getCompleted());
           return todoDto;
       }).collect(Collectors.toList());
    }
}
