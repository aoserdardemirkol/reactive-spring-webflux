package com.reactive.ws.reactive_spring_webflux.service;

import com.reactive.ws.reactive_spring_webflux.data.UserEntity;
import com.reactive.ws.reactive_spring_webflux.data.UserRepository;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final ReactiveAuthenticationManager reactiveAuthenticationManager;

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthenticationServiceImpl(ReactiveAuthenticationManager reactiveAuthenticationManager, UserRepository userRepository, JwtService jwtService) {
        this.reactiveAuthenticationManager = reactiveAuthenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public Mono<Map<String, String>> authenticate(String username, String password) {
        return reactiveAuthenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password))
                .then(getUserDetails(username))
                .map(this::createAuthResponse);
    }

    private Mono<UserEntity> getUserDetails(String username) {
        return userRepository.findByEmail(username);
    }

    private Map<String, String> createAuthResponse(UserEntity userEntity) {
        Map<String, String> result = new HashMap<>();
        result.put("userId", userEntity.getId().toString());
        result.put("token", jwtService.generateJwt(userEntity.getId().toString()));
        return result;
    }
}
