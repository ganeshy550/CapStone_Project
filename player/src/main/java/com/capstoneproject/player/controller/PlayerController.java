package com.capstoneproject.player.controller;

import com.capstoneproject.player.model.Player;
import com.capstoneproject.player.service.PlayerService;
import com.capstoneproject.player.dto.PlayerStatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        return ResponseEntity.ok(players);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable String id) {
        Player player = playerService.getPlayerById(id);
        if (player == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(player);
    }

    @PostMapping
    public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
        Player createdPlayer = playerService.addPlayer(player);
        return ResponseEntity.status(201).body(createdPlayer);  // 201 Created
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Player> updatePlayer(
            @PathVariable String id,
            @RequestBody Player updatedPlayer) {
        Player updated = playerService.updatePlayer(id, updatedPlayer);
        return ResponseEntity.ok(updated);  // 200 OK
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable String id) {
        try {
            playerService.deletePlayer(id);
            return ResponseEntity.ok("Player with ID " + id + " deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Player with ID " + id + " not found.");
        }
    }

    // New endpoint for finding player by email
    @GetMapping("find-by-email/{email}")
    public ResponseEntity<Player> getPlayerByEmail(@PathVariable String email) {
        Player player = playerService.getPlayerByEmail(email);
        if (player == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(player);
    }

    // New endpoint for finding player by phone
    @GetMapping("find-by-phone/{phone}")
    public ResponseEntity<Player> getPlayerByPhone(@PathVariable String phone) {
        Player player = playerService.getPlayerByPhone(phone);
        if (player == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(player);
    }

    // New endpoint to fetch player stats
    @GetMapping("stats/{playerId}")
    public ResponseEntity<PlayerStatDTO> getPlayerStats(@PathVariable String playerId) {
        try {
            PlayerStatDTO playerStats = playerService.getPlayerStats(playerId).block(); // Making the call to Player Stats Microservice
            if (playerStats == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(playerStats);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
