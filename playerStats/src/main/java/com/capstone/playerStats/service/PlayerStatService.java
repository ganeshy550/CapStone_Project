package com.capstone.playerStats.service;

import com.capstone.playerStats.dto.PlayerStatDTO;
import com.capstone.playerStats.model.PlayerStat;
import com.capstone.playerStats.repository.PlayerStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Service
public class PlayerStatService {

    private static final Logger logger = LoggerFactory.getLogger(PlayerStatService.class);

    @Autowired
    private PlayerStatsRepository playerStatRepository;

    public PlayerStatDTO getPlayerStats(String playerId) {
        logger.info("Fetching player stats for playerId: {}", playerId);
        Optional<PlayerStat> playerStatOptional = playerStatRepository.findByPlayerId(playerId);

        if (playerStatOptional.isEmpty()) {
            throw new RuntimeException("Player stats not found for ID: " + playerId);
        }

        return mapToDTO(playerStatOptional.get());
    }

    public PlayerStatDTO updatePlayerStats(String playerId, PlayerStatDTO updatedStats) {
        logger.info("Received request to update stats for playerId: {}, DTO: {}", playerId, updatedStats);

        // Validate incoming DTO
        if (!playerId.equals(updatedStats.getPlayerId())) {
            throw new IllegalArgumentException("Mismatch between path playerId and DTO playerId");
        }

        // Retrieve existing stats or throw an error if not found
        PlayerStat playerStat = playerStatRepository.findByPlayerId(playerId)
                .orElseThrow(() -> new RuntimeException("Player stats not found for ID: " + playerId));

        logger.info("Existing player stats before update: {}", playerStat);

        // Update the player stats
        playerStat.setTotalMatches(updatedStats.getTotalMatches());
        playerStat.setTotalRuns(updatedStats.getTotalRuns());
        playerStat.setTotalWickets(updatedStats.getTotalWickets());
        playerStat.setHighestRuns(updatedStats.getHighestRuns());

        // Save the updated player stats
        playerStat = playerStatRepository.save(playerStat);

        logger.info("Updated player stats after save: {}", playerStat);

        // Return the updated DTO
        return mapToDTO(playerStat);
    }

    private PlayerStatDTO mapToDTO(PlayerStat playerStat) {
        return new PlayerStatDTO(
                playerStat.getPlayerId(),
                playerStat.getTotalMatches(),
                playerStat.getTotalRuns(),
                playerStat.getTotalWickets(),
                playerStat.getHighestRuns()
        );
    }
}
