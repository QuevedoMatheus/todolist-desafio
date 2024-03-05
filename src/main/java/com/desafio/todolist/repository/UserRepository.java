package com.desafio.todolist.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.desafio.todolist.entity.User;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByUsername(String username);
}
