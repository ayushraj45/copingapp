package com.example.coping.service;

import com.example.coping.data.EntryRepository;
import com.example.coping.entities.Emotion;
import com.example.coping.entities.Entry;
import com.example.coping.entities.Questions;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EntryService {

    private final EntryRepository entryRepository;

    @Autowired
    private QuestionService questionService;

    @Autowired
    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    // Methods
    public List<Entry> findAllEntries() {
        return entryRepository.findAll();
    }

    public Emotion getEntryEmotion(Long entryId) {
        if (entryId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entry to return cannot have an empty ID");
        Entry entry = entryRepository.findById(entryId)
                .orElseThrow(() -> new EntityNotFoundException("Entry not found"));
        return entry.getEmotion();
    }


    public List<Questions> getAllQuestionsForAnEntry(Long entryId) {
        if (entryId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entry to return cannot have an empty ID");
        Entry entry = entryRepository.findById(entryId)
                .orElseThrow(() -> new EntityNotFoundException("Entry not found"));
        Emotion emotion = entry.getEmotion();
        return questionService.getAllQuestionWithEmotion(emotion.getId());
    }

    public Entry addAnEntry(Entry entry) {
        if (entry == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entry to add cannot be null");
        } else if (entry.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entry to add cannot contain an id");
        }
        return entryRepository.save(entry);
    }

    public Entry updateAnEntry(Entry entry) {
        if (entry == null || entry.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entry to update must have an id");
        } else if (!entryRepository.existsById(entry.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entry to update cannot be found");
        }
        return entryRepository.save(entry);
    }

    public void deleteAnEntry(Long entryId) {
        if (entryId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entry to delete must have an id");
        } else if (!entryRepository.existsById(entryId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entry to delete cannot be found");
        } else {
            entryRepository.deleteById(entryId);
        }
    }

}

