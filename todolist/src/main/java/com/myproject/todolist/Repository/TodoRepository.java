package com.myproject.todolist.Repository;

import com.myproject.todolist.Models.Todo;
import com.myproject.todolist.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {
    List<Todo> findByUser(User user);
    Boolean existsByIdAndUser(Long id, User user);
}
