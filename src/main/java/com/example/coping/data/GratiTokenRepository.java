package com.example.coping.data;

import com.example.coping.entities.GratiToken;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GratiTokenRepository extends ListCrudRepository<GratiToken, Long> {
}
