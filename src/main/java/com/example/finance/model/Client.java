package com.example.finance.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;          // now matches the renamed "id" column

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;  // maps to the existing "password" column

    // — Getters & Setters —

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
