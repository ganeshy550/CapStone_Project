package com.capstone.playerStats.controller;

import com.capstone.playerStats.dto.PlayerStatDTO;
import com.capstone.playerStats.service.PlayerStatService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/player-stats")
public class PlayerStatController {

    private static final Logger logger = LoggerFactory.getLogger(PlayerStatController.class);

    @Autowired
    private PlayerStatService playerStatService;

    @Operation(summary = "Get player stats by player ID")
    @GetMapping("get/{playerId}")
    public PlayerStatDTO getPlayerStats(@PathVariable String playerId) {
        logger.info("Fetching stats for playerId: {}", playerId);
        return playerStatService.getPlayerStats(playerId);
    }

    @Operation(summary = "Update player stats by player ID")
    @PutMapping("put/{playerId}")
    public PlayerStatDTO updatePlayerStats(
            @PathVariable String playerId,
            @RequestBody PlayerStatDTO playerStatDTO
    ) {
        logger.info("Received update request for playerId: {}, DTO: {}", playerId, playerStatDTO);

        // Validate playerId in path matches the one in the body
        if (!playerId.equals(playerStatDTO.getPlayerId())) {
            throw new IllegalArgumentException("Path playerId and body playerId do not match.");
        }

        // Perform the update
        PlayerStatDTO updatedStats = playerStatService.updatePlayerStats(playerId, playerStatDTO);
        logger.info("Successfully updated stats for playerId: {}", playerId);

        return updatedStats;
    }
}
