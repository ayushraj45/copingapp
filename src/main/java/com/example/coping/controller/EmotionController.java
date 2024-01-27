package com.example.coping.controller;

import com.example.coping.entities.Emotion;
import com.example.coping.service.EmotionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@SuppressWarnings({"unused", "UnusedReturnValue"})
@Tag(name = "Emotion Api")
@RequestMapping("/emotions")
public class EmotionController {

    private final EmotionService emotionService;

    @Autowired
    public EmotionController(EmotionService emotionService) {
        this.emotionService = emotionService;
    }

    @Operation(summary = "Get a list of all emotions", description = "Returns a list of all emotions")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Emotion> getAllEmotions () {
        return emotionService.findAllEmotions();
    }

    @Operation(summary = "Add an Emotion", description = "Add an emotion")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Emotion addEmotion(@RequestBody Emotion emotion) {
        return emotionService.addAnEmotion(emotion);
    }
    @Operation(summary = "Update an Emotion", description = "Update an emotion")
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Emotion updateEmotion(@RequestBody Emotion emotion) {
        return emotionService.updateAnEmotion(emotion);
    }

    @Operation(summary = "Delete an Emotion", description = "Delete an emotion")
    @DeleteMapping("/{emotionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEmotion(@PathVariable Long emotionId) {
         emotionService.deleteAnEmotion(emotionId);
    }
}
