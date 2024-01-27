package com.example.coping;

import com.example.coping.data.AppUsersRepository;
import com.example.coping.data.EmotionRepository;
import com.example.coping.data.EntryRepository;
import com.example.coping.data.QuestionRepository;
import com.example.coping.entities.AppUsers;
import com.example.coping.entities.Emotion;
import com.example.coping.entities.Questions;
import com.example.coping.entities.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataPopulator {

    private final AppUsersRepository appUsersRepository;
    private final EmotionRepository emotionRepository;
    private final EntryRepository entryRepository;
    private final QuestionRepository questionsRepository;

    @Autowired
    public DataPopulator(AppUsersRepository appUsersRepository, EmotionRepository emotionRepository,
                         EntryRepository entryRepository, QuestionRepository questionsRepository) {
        this.appUsersRepository = appUsersRepository;
        this.emotionRepository = emotionRepository;
        this.entryRepository = entryRepository;
        this.questionsRepository = questionsRepository;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void populateData() {
        // Dummy data for AppUsers
        AppUsers user1 = new AppUsers("user1");
        AppUsers user2 = new AppUsers("user2");

        appUsersRepository.save(user1);
        appUsersRepository.save(user2);

        // Dummy data for Emotion
        Emotion happy = new Emotion("Happy", "Feeling joyful");
        Emotion sad = new Emotion("Sad", "Feeling down");

        emotionRepository.save(happy);
        emotionRepository.save(sad);

        // Dummy data for Entry
        Entry entry1 = new Entry("Entry 1", "Content 1", happy, user1);
        Entry entry2 = new Entry("Entry 2", "Content 2", sad, user2);

        entryRepository.save(entry1);
        entryRepository.save(entry2);

        // Dummy data for Questions
        Questions question1 = new Questions("How are you feeling today?", happy);
        Questions question2 = new Questions("What makes you sad?", sad);

        questionsRepository.save(question1);
        questionsRepository.save(question2);
    }
}
