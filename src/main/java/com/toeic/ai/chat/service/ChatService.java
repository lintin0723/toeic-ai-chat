
package com.toeic.ai.chat.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChatService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String ollamaUrl = "http://localhost:11434/api/generate";

    public String getOllamaResponse(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "llama3");
        body.put("prompt", prompt);
        body.put("stream", false);


        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        Map<String, Object> response = restTemplate.postForObject(ollamaUrl, entity, Map.class);

        return (String) response.get("response");
    }
}
