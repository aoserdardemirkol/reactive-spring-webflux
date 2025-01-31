package com.reactive.ws.reactive_spring_webflux.presentation;

import com.reactive.ws.reactive_spring_webflux.presentation.model.AuthenticationRequest;
import com.reactive.ws.reactive_spring_webflux.service.AuthenticationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<Object>> authenticate(@RequestBody Mono<AuthenticationRequest> authenticationRequestMono) {
        return authenticationRequestMono
                .flatMap(authenticationRequest -> authenticationService.authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword()))
                .map(authenticationResultMap -> ResponseEntity.ok()
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "
                                + authenticationResultMap.get("token"))
                        .header("UserId", authenticationResultMap.get("userId"))
                        .build());
    }
}
