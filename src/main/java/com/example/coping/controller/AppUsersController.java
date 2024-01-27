package com.example.coping.controller;

import com.example.coping.entities.AppUsers;
import com.example.coping.entities.Entry;
import com.example.coping.service.AppUsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@SuppressWarnings({"unused", "UnusedReturnValue"})
@Tag(name = "User Api")
@RequestMapping("/appusers")
public class AppUsersController {
    private final AppUsersService appUserService;

    @Autowired
    public AppUsersController(AppUsersService appUserService) {
        this.appUserService = appUserService;
    }

    @Operation(summary = "Get a list of all app users", description = "Returns a list of all app users")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AppUsers> getAllAppUsers() {
        return appUserService.findAllAppUsers();
    }

    @Operation(summary = "Get a list of all app user's entries", description = "Returns a list of all entries by a users")
    @GetMapping("/{userId}/entries")
    @ResponseStatus(HttpStatus.OK)
    public List<Entry> getAllUserEntries(@PathVariable Long userId){
        return appUserService.getAllUserEntries(userId);
    }

    @Operation(summary = "Get an app user's subscription status", description = "Get an app user's subscription status")
    @GetMapping("/{userId}/subscription")
    @ResponseStatus(HttpStatus.OK)
    public String getUserSubscriptionStatus(@PathVariable Long userId){
        return appUserService.getUserSubscriptionStatus(userId);
    }



    @Operation(summary = "Add an AppUser", description = "Add an app user")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppUsers addAppUser(@RequestBody AppUsers appUser) {
        return appUserService.addAnAppUser(appUser);
    }

    @Operation(summary = "Update an AppUser", description = "Update an app user")
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppUsers updateAppUser(@RequestBody AppUsers appUser) {
        return appUserService.updateAnAppUser(appUser);
    }
    @Operation(summary = "Update an AppUser subscription status", description = "Update an app user subscription status")
    @PutMapping("/{appUserId}/subscription/update")
    @ResponseStatus(HttpStatus.CREATED)
    public AppUsers updateAppUserSubscriptionStatus(@RequestBody Long appUserId, String status) {
        return appUserService.updateAnAppUserSubscriptionStatus(appUserId, status);
    }

    @Operation(summary = "Delete an AppUser", description = "Delete an app user")
    @DeleteMapping("/{appUserId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAppUser(@PathVariable Long appUserId) {
        appUserService.deleteAnAppUser(appUserId);
    }
}
