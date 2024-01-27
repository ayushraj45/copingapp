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

    private String answer;

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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }
}
