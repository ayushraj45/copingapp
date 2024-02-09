package com.example.coping.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;

    @OneToMany
    private List<Questions> aiQuestions;
    private String contentForAi;
    private String content;
    private LocalDateTime timeStamp;
    @OneToOne
    private Emotion emotion;
    @ManyToOne
    @JoinColumn(name = "app_user_id")
    @JsonBackReference
    private AppUsers appUser;

    //Constructors

    public Entry() {
    }

    public Entry(String title, String content, Emotion emotion, String contentForAi, AppUsers appUser) {
        this.title = title;
        this.contentForAi=contentForAi;
        this.content = content;
        this.emotion = emotion;
        this.appUser = appUser;
    }

    //Setters and Getters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Emotion getEmotion() {
        return emotion;
    }

    public void setEmotion(Emotion emotion) {
        this.emotion = emotion;
    }

    public AppUsers getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUsers appUser) {
        this.appUser = this.appUser;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Long getId() {
        return id;
    }

    public List<Questions> getAiQuestions() {
        return aiQuestions;
    }

    public void setAiQuestions(List<Questions> aiQuestions) {
        this.aiQuestions = aiQuestions;
    }

    public String getContentForAi() {
        return contentForAi;
    }

    public void setContentForAi(String contentForAi) {
        this.contentForAi = contentForAi;
    }
}
