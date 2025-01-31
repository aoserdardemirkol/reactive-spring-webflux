package com.reactive.ws.reactive_spring_webflux.service;

import reactor.core.publisher.Mono;

public interface JwtService {
    String generateJwt(String subject);

    Mono<Boolean> validateJwt(String token);

    String extractTokenSubject(String token);
}
