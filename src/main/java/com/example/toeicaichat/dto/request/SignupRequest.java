package com.example.toeicaichat.dto.request;

// This DTO is used to transfer user registration data from the client.
public class SignupRequest {
    private String username;
    private String email; // ADDED EMAIL FIELD
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // ADDED GETTER AND SETTER FOR EMAIL
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
