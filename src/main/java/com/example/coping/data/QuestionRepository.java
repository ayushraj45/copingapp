package com.example.coping.data;

import com.example.coping.controller.QuestionController;
import com.example.coping.entities.Emotion;
import com.example.coping.entities.Questions;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends ListCrudRepository<Questions, Long> {
}
