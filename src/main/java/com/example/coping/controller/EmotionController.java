package com.example.coping.controller;

import com.example.coping.service.EmotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@SuppressWarnings({"unused", "UnusedReturnValue"})
//@Tag(name = "Amphibian Api")
@RequestMapping("/emotions")
public class EmotionController {

    private final EmotionService emotionService;

    @Autowired
    public EmotionController(EmotionService emotionService) {
        this.emotionService = emotionService;
    }

    @Operation(summary = "Get a list of all amphibians", description = "Returns a list of all amphibians")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
}
