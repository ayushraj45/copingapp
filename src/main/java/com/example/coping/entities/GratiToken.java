package com.example.coping.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class GratiToken {
    //Fields
    @Id
    @GeneratedValue
    Long id;
    @ManyToOne
    @JoinColumn(name = "app_user_id")
    @JsonBackReference
    private AppUsers appUser;
    private String prompt;

    private LocalDate createdAt;

    private int daysSinceLastToken=0;

    //Constructor
    public GratiToken() {}
    public GratiToken(AppUsers appUser) {
        this.appUser=appUser;
    }
    public GratiToken(String prompt,  AppUsers appUser) {
        this.prompt = prompt;
        this.appUser=appUser;
    }

    //Setters and Getters and More
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public int getDaysSinceLastToken() {
        return daysSinceLastToken;
    }

    public void setDaysSinceLastToken(int daysSinceLastToken) {
        this.daysSinceLastToken = daysSinceLastToken;
    }

    public Long getId() {
        return id;
    }
}
