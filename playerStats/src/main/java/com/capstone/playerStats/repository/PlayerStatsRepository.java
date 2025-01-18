package com.capstone.playerStats.repository;

import com.capstone.playerStats.model.PlayerStat;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PlayerStatsRepository extends MongoRepository<PlayerStat,Long> {
    Optional<PlayerStat> findByPlayerId(String playerId);
}
