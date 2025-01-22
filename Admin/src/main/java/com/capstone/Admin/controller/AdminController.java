package com.capstone.Admin.controller;

import com.capstone.Admin.dto.UserDTO;
import com.capstone.Admin.dto.TrainerDTO;
import com.capstone.Admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Create a new trainer
    @PostMapping("/trainers")
    public Mono<TrainerDTO> createTrainer(@RequestBody TrainerDTO trainerDTO) {
        return adminService.createTrainer(trainerDTO)
                .onErrorResume(e -> Mono.error(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Error creating trainer")));
    }

    // View all users
    @GetMapping("/users")
    public Mono<UserDTO[]> getAllUsers() {
        return adminService.getAllUsers()
                .onErrorResume(e -> Mono.error(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Error fetching users")));
    }

    // View all trainers
    @GetMapping("/trainers")
    public Mono<TrainerDTO[]> getAllTrainers() {
        return adminService.getAllTrainers()
                .onErrorResume(e -> Mono.error(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Error fetching trainers")));
    }

    // View trainers by city
    @GetMapping("/trainers/city")
    public Mono<TrainerDTO[]> getTrainersByCity(@RequestParam String city) {
        return adminService.getTrainersByCity(city)
                .onErrorResume(e -> Mono.error(new HttpClientErrorException(HttpStatus.NOT_FOUND, "Trainers not found in this city")));
    }
}

