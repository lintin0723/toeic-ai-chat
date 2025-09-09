package com.example.toeicaichat.controller;

import com.example.toeicaichat.dto.request.ChatRequest;
import com.example.toeicaichat.dto.response.ChatResponse;
import com.example.toeicaichat.service.OllamaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

    @Autowired
    private OllamaService ollamaService;

    @PostMapping("/chat")
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest chatRequest) {
        // DEBUG: Log to confirm the request reaches the controller
        System.out.println("--- DEBUG: /api/chat endpoint reached with message: " + chatRequest.getMessage() + " ---");

        String reply = ollamaService.generate(chatRequest.getMessage());
        return ResponseEntity.ok(new ChatResponse(reply));
    }
}
