package com.example.coping.service;

import com.example.coping.data.EmotionRepository;
import com.example.coping.data.QuestionRepository;
import com.example.coping.entities.Emotion;
import com.example.coping.entities.Questions;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    private final EmotionRepository emotionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, EmotionRepository emotionRepository) {
        this.questionRepository = questionRepository;
        this.emotionRepository=emotionRepository;
    }

    public List<Questions> getAllQuestionWithEmotion(Long emotionId) {
        if (emotionId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "EmotionId cannot be null");
        }
        Emotion emotion = emotionRepository.findById(emotionId)
                .orElseThrow(() -> new EntityNotFoundException("Emotion not found"));
        List<Questions> emotionBasedQuestionSet = emotion.getQuestionsList();
        return emotionBasedQuestionSet;
    }

    // Methods
    public List<Questions> findAllQuestions() {
        return questionRepository.findAll();
    }

    public Questions addAQuestion(Questions question) {
        if (question == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Question to add cannot be null");
        } else if (question.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Question to add cannot contain an id");
        }
        return questionRepository.save(question);
    }

    public Questions updateAQuestion(Questions question) {
        if (question == null || question.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Question to update must have an id");
        } else if (!questionRepository.existsById(question.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question to update cannot be found");
        }
        return questionRepository.save(question);
    }

    public Questions updateAQuestionAnswer(Questions question,String answer) {
        if (question == null || question.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Question to update must have an id");
        } else if (!questionRepository.existsById(question.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question to update cannot be found");
        }
        question.setAnswer(answer);
        return questionRepository.save(question);
    }

    public void deleteAQuestion(Long questionId) {
        if (questionId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Question to delete must have an id");
        } else if (!questionRepository.existsById(questionId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question to delete cannot be found");
        } else {
            questionRepository.deleteById(questionId);
        }
    }

}
