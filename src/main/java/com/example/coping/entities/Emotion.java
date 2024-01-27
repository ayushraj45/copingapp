package com.example.coping.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Emotion {

    @Id
    @GeneratedValue
    private Long id;
    private String emotion;
    private String description;

    @OneToMany
    private List<Questions> questionsList;

    //Constructors
    public Emotion() {
    }

    public Emotion(String emotion) {
        this.emotion = emotion;
    }

    //Setters and getters

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public List<Questions> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List<Questions> questionsList) {
        this.questionsList = questionsList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }
}
