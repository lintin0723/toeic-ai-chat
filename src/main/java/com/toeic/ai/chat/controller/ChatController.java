
package com.toeic.ai.chat.controller;

import com.toeic.ai.chat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public String getChatResponse(@RequestBody Map<String, String> payload) {
        String prompt = payload.get("prompt");
        return chatService.getOllamaResponse(prompt);
    }
}
