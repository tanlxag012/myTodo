package com.myproject.todolist.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "_todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private Boolean completed;
    @ManyToOne
    @JoinColumn(name = "_user_id")
    private User user;
    public Todo(){};
}
