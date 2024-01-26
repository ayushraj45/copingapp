package com.example.coping.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Questions {

    @Id
    @GeneratedValue
    Long id;
    private String questions;
    @OneToOne
    private Emotion emotion;

    //Constructor

    public Questions() {
    }

    public Questions(String questions, Emotion emotion) {
        this.questions = questions;
        this.emotion = emotion;
    }

    //Setters and Getters


    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public Emotion getEmotion() {
        return emotion;
    }

    public void setEmotion(Emotion emotion) {
        this.emotion = emotion;
    }
}
