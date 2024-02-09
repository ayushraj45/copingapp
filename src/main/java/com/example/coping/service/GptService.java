package com.example.coping.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GptService {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Value("${openai.api.key}")
    private String apiKey;

    public List<String> generateQuestions(String userEntry) {
        // Implement logic to send userEntry to GPT-3.5 and get the generated questions
        String gptApiResponse = callGptApi(userEntry);

        // Parse the GPT response and extract the generated questions
        List<String> generatedQuestions = parseGptResponse(gptApiResponse);

        return generatedQuestions;
    }

    private String callGptApi(String userEntry) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + apiKey); // Set the Authorization header with the API key

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", model); // Specify the desired GPT-3.5 model
        requestBody.put("messages", Collections.singletonList(Collections.singletonMap("content", userEntry)));
        // Add any other parameters required by the GPT-3.5 Chat API

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = new RestTemplate().postForEntity(apiUrl, requestEntity, String.class);

        return responseEntity.getBody();
//        // Make a POST request to the GPT-3.5 API
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBearerAuth(apiKey);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        Map<String, Object> requestBody = new HashMap<>();
//        requestBody.put("model", model);
//        requestBody.put("prompt", userEntry);
//        // Add any other parameters required by the GPT-3.5 API
//
//        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
//
//        ResponseEntity<String> responseEntity = new RestTemplate().postForEntity(apiUrl, requestEntity, String.class);
//
//        return responseEntity.getBody();
    }

    private List<String> parseGptResponse(String gptApiResponse) {
        // Implement logic to parse the GPT response and extract the generated questions
        // Return a list of questions
        // Example parsing logic: return List.of("Question 1", "Question 2", "Question 3", "Question 4", "Question 5");
        return Collections.emptyList(); // Placeholder, replace with actual parsing logic
    }
}

