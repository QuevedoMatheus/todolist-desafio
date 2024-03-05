package com.desafio.todolist.entity;


import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="tb_users")
public class User {
    @Id
    private String username;
    private String password;

    public User(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
