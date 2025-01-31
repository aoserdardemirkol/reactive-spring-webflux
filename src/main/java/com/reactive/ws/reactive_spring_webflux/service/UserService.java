package com.reactive.ws.reactive_spring_webflux.service;

import com.reactive.ws.reactive_spring_webflux.presentation.model.CreateUserRequest;
import com.reactive.ws.reactive_spring_webflux.presentation.model.UserRest;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserService extends ReactiveUserDetailsService {

    Mono<UserRest> createUser(Mono<CreateUserRequest> createUserRequestMono);

    Mono<UserRest> getUserById(UUID id);

    Flux<UserRest> findAll(int page, int limit);
}