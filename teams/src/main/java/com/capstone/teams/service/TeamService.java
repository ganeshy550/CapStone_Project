package com.capstone.teams.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.teams.entity.Team;
import com.capstone.teams.repository.TeamRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public Mono<Void> createTeamsForMatch(String matchId, List<String> teamNames, int teamSize) {
        if (teamNames.size() != 2) {
            return Mono.error(new IllegalArgumentException("Exactly two team names must be provided."));
        }

        Team team1 = new Team(null, matchId, new ArrayList<>(), teamSize);
        team1.setTeamName(teamNames.get(0));

        Team team2 = new Team(null, matchId, new ArrayList<>(), teamSize);
        team2.setTeamName(teamNames.get(1));

        return teamRepository.saveAll(List.of(team1, team2)).then();
    }

    public Mono<Team> registerUser(String matchId, String userId) {
        return teamRepository.findAllByMatchId(matchId)
                .filter(team -> team.getTeam().size() < team.getTeamSize()) // Filter teams with available space
                .next() // Get the first team with space
                .flatMap(team -> {
                    team.getTeam().add(userId); // Add the user to the team
                    return teamRepository.save(team); // Save the updated team
                })
                .switchIfEmpty(Mono.error(new RuntimeException("No available teams for match: " + matchId)));
    }
    

    public Flux<Team> getTeamDetails(String matchId) {
        return teamRepository.findAllByMatchId(matchId);
    }
}
