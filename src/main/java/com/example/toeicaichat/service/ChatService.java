package com.example.toeicaichat.service;

import org.springframework.beans.factory.annotation.Value;
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

    // FIX 1: Inject the URL from application properties instead of hardcoding to localhost
    @Value("${ollama.api.url}")
    private String ollamaUrl;

    public String getOllamaResponse(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = new HashMap<>();
        // FIX 2: Use the correct model name 'gemma:7b' which we downloaded
        body.put("model", "gemma:7b");
        body.put("prompt", prompt);
        body.put("stream", false);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            Map<String, Object> response = restTemplate.postForObject(ollamaUrl, entity, Map.class);
            if (response != null && response.containsKey("response")) {
                return (String) response.get("response");
            }
            // Handle cases where the response from Ollama is not as expected
            System.err.println("Invalid response from Ollama: " + response);
            return "Sorry, the AI returned an unexpected response format.";
        } catch (Exception e) {
            // Log the error to the backend console to see what's wrong
            System.err.println("Error calling Ollama API: " + e.getMessage());
            // Re-throw the exception to let Spring handle it (results in a 500 error for the client)
            throw e;
        }
    }
}
