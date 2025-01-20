package com.capstone.cricketmatch.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.cricketmatch.entity.Match;
import com.capstone.cricketmatch.repository.MatchRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MatchService {
    @Autowired
    private MatchRepository matchRepository;

    public Mono<Match> createMatch(Match match) {
        return matchRepository.save(match);
    }

    public Flux<Match> getAllMatches(){
        return matchRepository.findAll();
    }

    public Mono<Match> getMatchByCode(String code){
        return matchRepository.findByCode(code);
    }

    public Flux<Match> getMatchesByDate(Date date){
        return matchRepository.findByDate(date);
    }

    public Flux<Match> getMatchesByLocation(String location){
        return matchRepository.findByLocation(location);
    }


    public Mono<Match> updateMatchStatus(Long id,String winner) {
        return matchRepository.findById(id)
        .flatMap(match -> {
            match.setStatus("Completed");
            match.setWinner(winner);
            return matchRepository.save(match);
        })
        .switchIfEmpty(Mono.error(new RuntimeException("Match not found")));
    }

    public Mono<Match> startMatch(Long id) {
        return matchRepository.findById(id)
        .flatMap(match -> {
            match.setStatus("Ongoing");
            return matchRepository.save(match);
        })
        .switchIfEmpty(Mono.error(new RuntimeException("Match not found")));
    }

}
