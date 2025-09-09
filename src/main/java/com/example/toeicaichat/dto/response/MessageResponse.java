package com.example.toeicaichat.dto.response;

// A generic DTO for sending back simple string messages to the client.
public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
