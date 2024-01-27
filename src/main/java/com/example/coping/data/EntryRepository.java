package com.example.coping.data;

import com.example.coping.entities.Entry;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends ListCrudRepository<Entry, Long> {
}
