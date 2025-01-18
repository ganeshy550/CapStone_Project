package com.capstoneproject.player.dto;

import lombok.Data;

@Data
public class PlayerStatDTO {
    private String playerId;
    private int totalMatches;
    private int totalRuns;
    private int totalWickets;
    private float highestRuns;

    public PlayerStatDTO() {}

    public PlayerStatDTO(String playerId, int totalMatches, int totalRuns, int totalWickets, float highestRuns) {
        this.playerId = playerId;
        this.totalMatches = totalMatches;
        this.totalRuns = totalRuns;
        this.totalWickets = totalWickets;
        this.highestRuns = highestRuns;
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
