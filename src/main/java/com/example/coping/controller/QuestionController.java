package com.example.coping.controller;
import com.example.coping.entities.Emotion;
import com.example.coping.entities.Questions;
import com.example.coping.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@SuppressWarnings({"unused", "UnusedReturnValue"})
@Tag(name = "Question Api")
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Operation(summary = "Get a list of all questions", description = "Returns a list of all questions")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Questions> getAllQuestions() {
        return questionService.findAllQuestions();
    }

    @Operation(summary = "Get a list of questions based on given Emotion", description = "returns a question set corresponding to an emotion")
    @GetMapping("/{emotionId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Questions> getQuestionsByEmotion(@PathVariable Long emotionId){
        return questionService.getAllQuestionWithEmotion(emotionId);
    }

    @Operation(summary = "Add a Question", description = "Add a question")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Questions addQuestion(@RequestBody Questions question) {
        return questionService.addAQuestion(question);
    }

    @Operation(summary = "Update a Question", description = "Update a question")
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Questions updateQuestion(@RequestBody Questions question) {
        return questionService.updateAQuestion(question);
    }

    @Operation(summary = "Update a Question Answer", description = "Updates the answer to a question")
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Questions updateQuestionAsnwer(@RequestBody Questions question, @RequestBody String answer) {
        return questionService.updateAQuestionAnswer(question, answer);
    }

    @Operation(summary = "Delete a Question", description = "Delete a question")
    @DeleteMapping("/{questionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuestion(@PathVariable Long questionId) {
        questionService.deleteAQuestion(questionId);
    }
}
