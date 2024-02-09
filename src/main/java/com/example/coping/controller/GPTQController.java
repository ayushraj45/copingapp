package com.example.coping.controller;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@SuppressWarnings({"unused", "UnusedReturnValue"})
@Tag(name = "GPTQs Api")
@RequestMapping("/gptq")
public class GPTQController {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Value("${openai.api.key}")
    private String apiKey;

//    @Autowired
//    private RestTemplate template;
//
//    private final GptService gptService;

//    @Autowired
//    public GPTQController(GptService gptService) {
//        this.gptService = gptService;
//    }

    @GetMapping("/chat")
    public String createQuestions(@RequestParam String prompt){
        OpenAiService service = new OpenAiService(apiKey);
        List<ChatMessage> messages = new ArrayList<ChatMessage>();
        ChatMessage message = new ChatMessage("user", prompt);
        messages.add(message);
        ChatCompletionRequest completionRequest = ChatCompletionRequest
                .builder()
                .messages(messages)
                .model(model)
                .temperature(1.0)
                .n(1)
                .build();
        List<ChatCompletionChoice> choices = service.createChatCompletion(completionRequest).getChoices();

        String returnString = "";
        for(ChatCompletionChoice choice: choices){
            returnString += choice.getMessage().getContent()+System.lineSeparator();
        }
        return returnString;
    }



//    public String chat(@RequestParam("prompt") String prompt){
//
//        ChatGPTRequest chatGPTRequest = new ChatGPTRequest(model, prompt);
//
//        ChatGPTResponse chatGPTResponse = template.postForObject(apiUrl, chatGPTRequest, ChatGPTResponse.class);
//
//        return chatGPTResponse.getChoices().get(0).getText();
//    }
//    @PostMapping("/generate-questions")
//    public List<String> generateQuestions(@RequestBody String userEntry) {
//        return gptService.generateQuestions(userEntry);
//    }

//    @GetMapping("/chat")
//    public String chat(@RequestParam("prompt") String prompt){
//        ChatGPTRequest request=new ChatGPTRequest(model,prompt);
//        ChatGPTResponse chatGPTResponse = template.postForObject(apiUrl, request, ChatGPTResponse.class);
//        return chatGPTResponse.getChoices().get(1).getMessage().getContent();
//    }
}
