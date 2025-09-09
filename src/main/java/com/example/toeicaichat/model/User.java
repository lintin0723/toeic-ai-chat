package com.example.toeicaichat.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = true) // email can be optional
    private String email;

    // THE FIX IS HERE: Match the field name to the database column 'password_hash'
    // Spring's naming strategy will convert 'passwordHash' to 'password_hash'.
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // THE FIX IS HERE: This method MUST be named getPassword() for Spring Security to work correctly.
    // It should return the hashed password from the database.
    public String getPassword() {
        return passwordHash;
    }

    public void setPassword(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
