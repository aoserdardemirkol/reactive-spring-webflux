package com.reactive.ws.reactive_spring_webflux.service;

public interface JwtService {
    String generateJwt(String subject);
}
