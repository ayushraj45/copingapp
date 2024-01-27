package com.example.coping.service;

import com.example.coping.data.AppUsersRepository;
import com.example.coping.data.EntryRepository;
import com.example.coping.entities.AppUsers;
import com.example.coping.entities.Entry;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AppUsersService {

        private final AppUsersRepository appUserRepository;
        private final EntryRepository entryRepository;

        @Autowired
        private EntryService entryService;

        @Autowired
        public AppUsersService(AppUsersRepository appUserRepository, EntryRepository entryRepository) {
            this.appUserRepository = appUserRepository;
            this.entryRepository = entryRepository;
        }

    // Methods
        public List<AppUsers> findAllAppUsers() {
            return appUserRepository.findAll();
        }

        public List<Entry> getAllUserEntries(Long userId) {
            if (userId == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "AppUser to delete must have an id");
            }
            AppUsers user = appUserRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
            List<Entry> userEntries = user.getEntries();
            return userEntries;
        }

        public String getUserSubscriptionStatus(Long userId) {
            if (userId == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "AppUser to delete must have an id");
            }
            AppUsers user = appUserRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
            String subscriptionStatus  = user.getSubscriptionStatus();
            return subscriptionStatus;
        }

        public AppUsers addAnAppUser(AppUsers appUser) {
            if (appUser == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "AppUser to add cannot be null");
            } else if (appUser.getId() != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "AppUser to add cannot contain an id");
            }
            return appUserRepository.save(appUser);
        }

        public AppUsers updateAnAppUser(AppUsers appUser) {
            if (appUser == null || appUser.getId() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "AppUser to update must have an id");
            } else if (!appUserRepository.existsById(appUser.getId())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AppUser to update cannot be found");
            }
            return appUserRepository.save(appUser);
        }

    public AppUsers updateAnAppUserSubscriptionStatus(Long appUserId, String subStatus) {
        if (appUserId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "AppUser to delete must have an id");
        }
        AppUsers appUser = appUserRepository.findById(appUserId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        appUser.setSubscriptionStatus(subStatus);
        return appUserRepository.save(appUser);
    }

        public Entry updateUserEntry(Long userId, Entry entry) {
            if (userId == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "AppUser to delete must have an id");
            }
            AppUsers user = appUserRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
            entryService.updateAnEntry(entry);
            appUserRepository.save(user);
            return entry;
        }

        public void deleteUserEntry(Long userId, Entry entry) {
            if (userId == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "AppUser to delete must have an id");
            }
            AppUsers user = appUserRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
            entryService.deleteAnEntry(entry.getId());
            appUserRepository.save(user);
        }

        public void deleteAnAppUser(Long appUserId) {
            if (appUserId == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "AppUser to delete must have an id");
            } else if (!appUserRepository.existsById(appUserId)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AppUser to delete cannot be found");
            } else {
                appUserRepository.deleteById(appUserId);
            }
        }



}
