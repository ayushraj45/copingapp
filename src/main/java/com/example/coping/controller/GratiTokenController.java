package com.example.coping.controller;

import com.example.coping.entities.GratiToken;
import com.example.coping.service.GratiTokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@SuppressWarnings({"unused", "UnusedReturnValue"})
@Tag(name = "Gratitoken API")
@RequestMapping("/gratitoken")
public class GratiTokenController {

    private GratiTokenService gratitokenService;

    @Autowired
    public GratiTokenController(GratiTokenService gratitokenService) {
        this.gratitokenService = gratitokenService;
    }

    @Operation(summary = "Get a list of all gratitokens", description = "Returns a list of all gratitokens")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GratiToken> getAllGratitokens() {
        return gratitokenService.findAllGratitokens();
    }

    @Operation(summary = "Get the creation timestamp of a gratitoken", description = "Returns the creation timestamp of a gratitoken")
    @GetMapping("/{appUserId}/dayssincelast")
    @ResponseStatus(HttpStatus.OK)
    public long getDaysSinceLastForAUser(@PathVariable Long appUserId) {
        return gratitokenService.getDaysSinceLast(appUserId);
    }

    @Operation(summary = "Get Days Since Last Gratitoken for user", description = "Returns the number of days since last gratitoken")
    @GetMapping("/{gratitokenId}/created-at")
    @ResponseStatus(HttpStatus.OK)
    public LocalDate getGratitokenCreatedAt(@PathVariable Long gratitokenId) {
        return gratitokenService.getCreatedAt(gratitokenId);
    }


    @Operation(summary = "Add a Gratitoken", description = "Add a Gratitoken")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GratiToken addGratitoken(@RequestBody GratiToken gratitoken) {
        return gratitokenService.addGratitoken(gratitoken);
    }

    @Operation(summary = "Update a Gratitoken", description = "Update a Gratitoken")
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GratiToken updateGratitoken(@RequestBody GratiToken gratitoken) {
        return gratitokenService.updateGratitoken(gratitoken);
    }

    @Operation(summary = "Delete a Gratitoken", description = "Delete a Gratitoken")
    @DeleteMapping("/{gratitokenId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGratitoken(@PathVariable Long gratitokenId) {
        gratitokenService.deleteGratitoken(gratitokenId);
    }
}
