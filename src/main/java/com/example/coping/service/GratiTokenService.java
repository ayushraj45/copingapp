package com.example.coping.service;

import com.example.coping.data.AppUsersRepository;
import com.example.coping.data.GratiTokenRepository;
import com.example.coping.entities.AppUsers;
import com.example.coping.entities.GratiToken;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class GratiTokenService {

    private final GratiTokenRepository gratitokenRepository;

    @Autowired
    private final AppUsersRepository appUserRepository;


    @Autowired
    public GratiTokenService(GratiTokenRepository gratitokenRepository, AppUsersRepository appUserRepository) {
        this.gratitokenRepository = gratitokenRepository;
        this.appUserRepository = appUserRepository;
    }

    // Methods
    public List<GratiToken> findAllGratitokens() {
        return gratitokenRepository.findAll();
    }

    public LocalDate getCreatedAt(Long gratitokenId) {
        if (gratitokenId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gratitoken to get creation timestamp cannot have an empty ID");

        GratiToken gratitoken = gratitokenRepository.findById(gratitokenId)
                .orElseThrow(() -> new EntityNotFoundException("Gratitoken not found"));

        return gratitoken.getCreatedAt();
    }

    public GratiToken addGratitoken(GratiToken gratitoken) {
        if (gratitoken == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gratitoken to add cannot be null");
        } else if (gratitoken.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gratitoken to add cannot contain an id");
        }
        return gratitokenRepository.save(gratitoken);
    }

    public GratiToken updateGratitoken(GratiToken gratitoken) {
        if (gratitoken == null || gratitoken.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gratitoken to update must have an id");
        } else if (!gratitokenRepository.existsById(gratitoken.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gratitoken to update cannot be found");
        }
        return gratitokenRepository.save(gratitoken);
    }

    public void deleteGratitoken(Long gratitokenId) {
        if (gratitokenId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gratitoken to delete must have an id");
        } else if (!gratitokenRepository.existsById(gratitokenId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gratitoken to delete cannot be found");
        } else {
            gratitokenRepository.deleteById(gratitokenId);
        }
    }

    public long getDaysSinceLast(Long appUserId) {
        long daysSinceLastgratiToken;

        if (appUserId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User id cannot be null");}
            AppUsers user = appUserRepository.findById(appUserId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
            List<GratiToken> usertokens = user.getGratiTokens();
            if (usertokens.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User has no gratiTokens yet");
            } else {
                GratiToken lastGratitoken = usertokens.get(usertokens.size() - 1);
                LocalDate currentDate = LocalDate.now();
                LocalDate lastGratitokendate = lastGratitoken.getCreatedAt();
                daysSinceLastgratiToken = ChronoUnit.DAYS.between(lastGratitokendate, currentDate);
            }
        return daysSinceLastgratiToken;

    }
}
