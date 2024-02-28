package com.desafio.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.todolist.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    
}
