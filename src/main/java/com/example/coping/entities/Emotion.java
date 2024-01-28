package com.example.coping.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Emotion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String emotion;
    private String description;
    @OneToMany(mappedBy = "emotion",cascade = CascadeType.ALL)
    private List<Questions> questionsList;

    //Constructors
    public Emotion() {
    }

    public Emotion(String emotion, String description) {
        this.emotion = emotion;
        this.description=description;
    }

    //Setters and getters

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }
    @JsonIgnore
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
