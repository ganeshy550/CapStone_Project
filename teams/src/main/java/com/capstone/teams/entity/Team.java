package com.capstone.teams.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "teams")
public class Team {
    @Id
    private Long id;
    private String matchId; // Unique identifier for the match
    private List<String> team;
    private int teamSize; // Maximum size of each team
    private int teamScore;
    private String teamName; // Name of the team
    
    public Team(){        
    }

    public Team(Long id, String matchId, List<String> team, int teamSize) {
        this.id = id;
        this.matchId = matchId;
        this.team = team;
        this.teamSize = teamSize;
        teamScore = 0;
    }



    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public List<String> getTeam() {
        return team;
    }

    public void setTeam(List<String> team) {
        this.team = team;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    public int getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(int teamScore) {
        this.teamScore = teamScore;
    }

    
}
    