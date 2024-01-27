package com.example.coping.service;

import com.example.coping.data.EmotionRepository;
import com.example.coping.entities.Emotion;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class EmotionService {
    private final EmotionRepository emotionRepository;

    public EmotionService(EmotionRepository emotionRepository) {
        this.emotionRepository = emotionRepository;
    }

    //Methods
    public List<Emotion> findAllEmotions() {
        return emotionRepository.findAll();
    }

    public Emotion addAnEmotion(Emotion emotion) {
        if (emotion == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Emotion to add cannot be null");
        }
        else if (emotion.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Emotion to add cannot contain an id");
        }
        return emotionRepository.save(emotion);
    }

    public Emotion updateAnEmotion(Emotion emotion) {
        if (emotion == null || emotion.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Emotion to update must have an id");
        }
        else if (!emotionRepository.existsById(emotion.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Emotion to update cannot be found");
        }
        return emotionRepository.save(emotion);
    }

    public void deleteAnEmotion(Long emotionId) {
        if (emotionId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Emotion to Delete must have an id");
        }
        else if (!emotionRepository.existsById(emotionId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Emotion to delete cannot be found");
        }
        else{
            emotionRepository.deleteById(emotionId);
        }
    }
}
