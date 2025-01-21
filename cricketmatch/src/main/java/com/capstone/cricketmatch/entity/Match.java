package com.capstone.cricketmatch.entity;

import java.util.Date;
import java.util.Random;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "matches")
public class Match {
    @Id
    private Long id;
    private String team1;
    private String team2;
    private Date date;
    private String location;
    private int teamSize;
    private String winner;
    private String status;
    private String code;
    
    public Match(Long id, String team1, String team2, Date date, String location, int teamSize) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;
        this.location = location;
        this.teamSize = teamSize;
        code = generateCode();
        winner = null;
        status = "Upcoming";
    }
 
    
    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTeam1() {
        return team1;
    }
    public void setTeam1(String team1) {
        this.team1 = team1;
    }
    public String getTeam2() {
        return team2;
    }
    public void setTeam2(String team2) {
        this.team2 = team2;
    }
    public String getWinner() {
        return winner;
    }
    public void setWinner(String winner) {
        this.winner = winner;
    }
    
    // Changed getter name to match field name for consistency
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) { 
        this.code = code;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public static String generateCode(){
        StringBuilder code = new StringBuilder();
        String possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            code.append(possible.charAt(random.nextInt(possible.length())));
        }
        return code.toString();
    }
}
