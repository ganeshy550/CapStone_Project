package com.capstone.playerStats.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "player_stats")
public class PlayerStat {

    @Id

    private String id;
    private String playerId; // Foreign Key to Player
    @JsonProperty("totalMatches")
    private int totalMatches;
    private int totalRuns;
    private int totalWickets;
    private float highestRuns;

    public PlayerStat() {}

    public PlayerStat(String id, String playerId, int totalMatches, int totalRuns, int totalWickets, float highestRuns) {
        this.id = id;
        this.playerId = playerId;
        this.totalMatches = totalMatches;
        this.totalRuns = totalRuns;
        this.totalWickets = totalWickets;
        this.highestRuns = highestRuns;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public int getTotalMatches() {
        return totalMatches;
    }

    public void setTotalMatches(int totalMatches) {
        this.totalMatches = totalMatches;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public void setTotalRuns(int totalRuns) {
        this.totalRuns = totalRuns;
    }

    public int getTotalWickets() {
        return totalWickets;
    }

    public void setTotalWickets(int totalWickets) {
        this.totalWickets = totalWickets;
    }

    public float getHighestRuns() {
        return highestRuns;
    }

    public void setHighestRuns(float highestRuns) {
        this.highestRuns = highestRuns;
    }
}
