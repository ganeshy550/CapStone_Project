// package com.capstone.teams.service;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;
// import org.springframework.web.reactive.function.client.WebClient;
// import reactor.core.publisher.Mono;

// @Service
// public class UserServiceClient {

//     private final WebClient webClient;

//     public UserServiceClient(@Value("${user.service.url}") String userServiceUrl) {
//         this.webClient = WebClient.builder().baseUrl(userServiceUrl).build();
//     }

//     public Mono<String> getUserDetails(String userId) {
//         return webClient.get()
//                 .uri("/api/users/{userId}", userId)
//                 .retrieve()
//                 .bodyToMono(String.class);
//     }
// }