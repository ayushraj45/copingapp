package com.example.coping.controller;

import com.example.coping.entities.Emotion;
import com.example.coping.entities.Entry;
import com.example.coping.entities.Questions;
import com.example.coping.service.EntryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@SuppressWarnings({"unused", "UnusedReturnValue"})
@Tag(name = "Entry Api")
@RequestMapping("/entry")
public class EntryController {

    private final EntryService entryService;

    @Autowired
    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @Operation(summary = "Get a list of all entries", description = "Returns a list of all entries")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Entry> getAllEntries() {
        return entryService.findAllEntries();
    }

    @Operation(summary = "Get a list of all entries", description = "Returns a list of all entries")
    @GetMapping("/{entryId}/emotion")
    @ResponseStatus(HttpStatus.OK)
    public Emotion getEntriesEmotion(@PathVariable Long entryId) {
        return entryService.getEntryEmotion(entryId);
    }

    @Operation(summary = "Get a list of all question for an entry", description = "Returns a question set based on Entry and Emotion")
    @GetMapping("/{entryId}/questions")
    @ResponseStatus(HttpStatus.OK)
    public List<Questions> getEntryQuestionSet(@PathVariable Long entryId) {
        return entryService.getAllQuestionsForAnEntry(entryId);
    }

    @Operation(summary = "Add an Entry", description = "Add an entry")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entry addEntry(@RequestBody Entry entry) {
        return entryService.addAnEntry(entry);
    }

    @Operation(summary = "Update an Entry", description = "Update an entry")
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entry updateEntry(@RequestBody Entry entry) {
        return entryService.updateAnEntry(entry);
    }

    @Operation(summary = "Delete an Entry", description = "Delete an entry")
    @DeleteMapping("/{entryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEntry(@PathVariable Long entryId) {
        entryService.deleteAnEntry(entryId);
    }
}

