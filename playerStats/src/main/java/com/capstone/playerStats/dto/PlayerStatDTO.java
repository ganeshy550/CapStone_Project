package com.capstone.playerStats.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerStatDTO {
//    @JsonProperty("playerId")
    private String playerId;
//    @JsonProperty("totalMatches")
    private int totalMatches;
//    @JsonProperty("totalRuns")
    private int totalRuns;
//    @JsonProperty("totalWickets")
    private int totalWickets;
//    @JsonProperty("highestRuns")
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
    @Override
    public String toString() {
        return "PlayerStatDTO{" +
                "playerId='" + playerId + '\'' +
                ", totalMatches=" + totalMatches +
                ", totalRuns=" + totalRuns +
                ", totalWickets=" + totalWickets +
                ", highestRuns=" + highestRuns +
                '}';
    }
}
