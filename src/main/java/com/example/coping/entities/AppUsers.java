package com.example.coping.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class AppUsers {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String subscriptionStatus=null;
    @OneToMany
    private List<Entry> entries = new ArrayList<>();
    private String password_hash;

    //Constructors
    public AppUsers(String username) {
        this.username = username;
    }

    public AppUsers() {
    }

    //Setters and Getters


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public void setSubscriptionStatus(String subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }
}
