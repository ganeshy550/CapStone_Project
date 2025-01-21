package com.capstone.Players.controller;

import com.capstone.Players.dto.BattingStatsDTO;
import com.capstone.Players.dto.BowlingStatsDTO;
import com.capstone.Players.dto.PlayerStatsDTO;
import com.capstone.Players.model.User;
import com.capstone.Players.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;



    // Create a new user
    @PostMapping
    public Mono<User> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Update an existing user
    @PutMapping("/{userId}")
    public Mono<User> updateUser(@PathVariable String userId, @RequestBody User user) {
        return userService.updateUser(userId, user);
    }

    // Get user by email
    @GetMapping("/email/{userEmail}")
    public Mono<User> findByUserEmail(@PathVariable String userEmail) {
        return userService.findByUserEmail(userEmail);
    }

    // Get user by phone number
    @GetMapping("/phone/{userPhone}")
    public Mono<User> findByUserPhone(@PathVariable String userPhone) {
        return userService.findByUserPhone(userPhone);
    }

    // Get player stats
    @GetMapping("/{userId}/stats")
    public Mono<PlayerStatsDTO> getPlayerStats(@PathVariable String userId) {
        return userService.getPlayerStats(userId);
    }

    // Fetch Batting Stats
    @GetMapping("/{userId}/batting-stats")
    public Mono<BattingStatsDTO> getBattingStats(@PathVariable String userId) {
        return userService.getBattingStats(userId);
    }

    // Fetch Bowling Stats
    @GetMapping("/{userId}/bowling-stats")
    public Mono<BowlingStatsDTO> getBowlingStats(@PathVariable String userId) {
        return userService.getBowlingStats(userId);
    }

    // Update player stats
    @PutMapping("/{userId}/update-stats")
    public Mono<ResponseEntity<User>> updatePlayerStats(
            @PathVariable String userId,
            @RequestBody PlayerStatsDTO playerStatsDTO) {

        return userService.updatePlayerStats(userId, playerStatsDTO)
                .map(user -> ResponseEntity.ok(user))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }
}
