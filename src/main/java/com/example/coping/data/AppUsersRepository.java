package com.example.coping.data;

import com.example.coping.entities.AppUsers;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUsersRepository extends ListCrudRepository<AppUsers, Long> {
}
