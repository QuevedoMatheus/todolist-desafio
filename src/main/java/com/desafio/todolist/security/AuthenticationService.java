package com.desafio.todolist.security;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public String authenticate(){
        return "token";
    }
}
