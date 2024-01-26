package com.example.coping.data;

import com.example.coping.entities.Emotion;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmotionRepository extends ListCrudRepository<Emotion, Long> {
}
