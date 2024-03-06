package com.desafio.todolist.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.desafio.todolist.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findAll(Sort sort);
    
}
