package com.capstone.teams.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamCreationRequest {
    private String matchId;
    private List<String> teamNames; // Two team names
    private int teamSize;
}
