package com.myproject.todolist.Service;

import com.myproject.todolist.Dto.TodoDto;
import com.myproject.todolist.Models.Todo;
import com.myproject.todolist.Models.User;
import com.myproject.todolist.Repository.TodoRepository;
import com.myproject.todolist.Repository.UserRepository;
import com.myproject.todolist.Security.Service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository;
    @Autowired
    UserRepository userRepository;
    public String getCurrentUsername(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetailsImpl){
            return ((UserDetailsImpl) principal).getUsername();
        }
        return null;
    }

    public List<TodoDto> getTodos() {
        User user = userRepository.findByUsername(getCurrentUsername());
       List<Todo> todos = todoRepository.findByUser(user);
       return todos.stream().map(todo -> {
           TodoDto todoDto = new TodoDto();
           todoDto.setId(todo.getId());
           todoDto.setText(todo.getText());
           todoDto.setCompleted(todo.getCompleted());
           return todoDto;
       }).collect(Collectors.toList());
    }

    public TodoDto addTodo(TodoDto todoDto) {
        User user = userRepository.findByUsername(getCurrentUsername());
        Todo todo = new Todo();
        todo.setText(todoDto.getText());
        todo.setUser(user);
        todoRepository.save(todo);
        return todoDto;
    }

    public ResponseEntity<String> deleteTodo(Long id) {
        User user = userRepository.findByUsername(getCurrentUsername());
        if(!todoRepository.existsByIdAndUser(id,user)){
            return new ResponseEntity<>("Todo not found",HttpStatus.NOT_FOUND);
        }
        Todo todo = new Todo();
        todo.setId(id);
        todo.setUser(user);
        todoRepository.delete(todo);
        return new ResponseEntity<>("Todo Deleted", HttpStatus.OK);
    }

    public ResponseEntity<String> toggleComplete(TodoDto todoDto) {
        User user = userRepository.findByUsername(getCurrentUsername());
        if(!todoRepository.existsByIdAndUser(todoDto.getId(),user)){
            return new ResponseEntity<>("Todo not found",HttpStatus.NOT_FOUND);
        }
        Optional<Todo> optionalTodo = todoRepository.findById(todoDto.getId());
        Todo todo = optionalTodo.get();
        todo.setId(todoDto.getId());
        todo.setUser(user);
        todo.setCompleted(todoDto.getCompleted());
        todoRepository.save(todo);
        return new ResponseEntity<>("Todo Toggled", HttpStatus.OK);
    }
}
