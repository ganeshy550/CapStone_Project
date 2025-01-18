package com.capstoneproject.player.service;

import com.capstoneproject.player.model.Player;
import com.capstoneproject.player.repository.PlayerRepository;
import com.capstoneproject.player.dto.PlayerStatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private WebClient.Builder webClientBuilder; // Autowire WebClient

    // The base URL of the Player Stats Microservice
    private final String PLAYER_STATS_URL = "http://localhost:9002/api/player-stats/get/";

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayerById(String playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player with ID " + playerId + " not found."));
    }

    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player updatePlayer(String playerId, Player updatedPlayerDetails) {
        Optional<Player> existingPlayerOptional = playerRepository.findById(playerId);

        if (existingPlayerOptional.isPresent()) {
            Player existingPlayer = existingPlayerOptional.get();

            existingPlayer.setName(updatedPlayerDetails.getName());
            existingPlayer.setEmail(updatedPlayerDetails.getEmail());
            existingPlayer.setPhone(updatedPlayerDetails.getPhone());
            existingPlayer.setTeamId(updatedPlayerDetails.getTeamId());

            return playerRepository.save(existingPlayer);
        } else {
            throw new RuntimeException("Player with ID " + playerId + " not found.");
        }
    }


    public void deletePlayer(String playerId) {
        if (playerRepository.existsById(playerId)) {
            playerRepository.deleteById(playerId);
        } else {
            throw new RuntimeException("Player with ID " + playerId + " not found.");
        }
    }

    // New method to find player by email
    public Player getPlayerByEmail(String email) {
        return playerRepository.findByEmail(email);
    }

    // New method to find player by phone
    public Player getPlayerByPhone(String phone) {
        return playerRepository.findByPhone(phone);
    }

    // New method to fetch player stats from Player Stats Microservice
    public Mono<PlayerStatDTO> getPlayerStats(String playerId) {
        return webClientBuilder.baseUrl(PLAYER_STATS_URL)
                .build()
                .get()
                .uri("/{playerId}", playerId) // Pass the playerId as path variable
                .retrieve()
                .bodyToMono(PlayerStatDTO.class); // Return the PlayerStatDTO
    }

}
