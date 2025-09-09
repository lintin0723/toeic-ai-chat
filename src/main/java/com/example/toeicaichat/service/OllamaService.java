package com.example.toeicaichat.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OllamaService {

    @Value("${OLLAMA_API_URL}")
    private String ollamaApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String generate(String prompt) {
        try {
            // Create the request body for the Ollama API
            OllamaRequest request = new OllamaRequest("gemma:7b", prompt, false);

            // Call the Ollama API
            OllamaResponse response = restTemplate.postForObject(ollamaApiUrl, request, OllamaResponse.class);

            if (response != null && response.getResponse() != null) {
                return response.getResponse();
            }
            return "Sorry, I couldn't get a response from the AI.";
        } catch (Exception e) {
            // Log the error for debugging
            e.printStackTrace();
            return "Error: Could not connect to the AI service. " + e.getMessage();
        }
    }

    // --- DTOs for Ollama API ---

    private static class OllamaRequest {
        private String model;
        private String prompt;
        private boolean stream;

        public OllamaRequest(String model, String prompt, boolean stream) {
            this.model = model;
            this.prompt = prompt;
            this.stream = stream;
        }

        // Getters are needed for Jackson serialization
        public String getModel() { return model; }
        public String getPrompt() { return prompt; }
        public boolean isStream() { return stream; }
    }

    // THE FIX IS HERE: Ignore any unknown properties from the Ollama API response
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class OllamaResponse {
        private String model;
        private String response;
        private boolean done;

        @JsonProperty("created_at")
        private String createdAt;

        @JsonProperty("total_duration")
        private long totalDuration;

        // Getters and Setters are needed for Jackson deserialization
        public String getModel() { return model; }
        public void setModel(String model) { this.model = model; }
        public String getResponse() { return response; }
        public void setResponse(String response) { this.response = response; }
        public boolean isDone() { return done; }
        public void setDone(boolean done) { this.done = done; }
        public String getCreatedAt() { return createdAt; }
        public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
        public long getTotalDuration() { return totalDuration; }
        public void setTotalDuration(long totalDuration) { this.totalDuration = totalDuration; }
    }
}
