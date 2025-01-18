package com.capstoneproject.player.repository;

import com.capstoneproject.player.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {
    Player findByEmail(String email);
    Player findByPhone(String phone);  // New method for phone lookup
}

