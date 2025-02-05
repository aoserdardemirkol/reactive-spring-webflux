package com.reactive.ws.users.presentation;

import com.reactive.ws.users.presentation.model.CreateUserRequest;
import com.reactive.ws.users.presentation.model.UserRest;
import com.reactive.ws.users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/users") //   http://localhost:8080/users
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Mono<ResponseEntity<UserRest>> createUser(@RequestBody @Valid Mono<CreateUserRequest> createUserRequest) {
        return userService.createUser(createUserRequest)
                .map(userRest -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .location(URI.create("/users/" + userRest.getId()))
                        .body(userRest));
    }

    @GetMapping("/{userId}")
    // Spring Security Expression Language (SpEL) - Allows you to define spring security rules and access controls in your spring applications
    // @PreAuthorize("authentication.principal.equals(#userId.toString()) or hasAnyRole('ROLE_ADMIN')")
    @PostAuthorize("returnObject.body!=null and (returnObject.body.id.toString().equals(authentication.principal))")
    public Mono<ResponseEntity<UserRest>> getUser(@PathVariable("userId") UUID userId,
                                                  @RequestParam(name = "include", required = false) String include,
                                                  @RequestHeader(name = "Authorization") String jwt) {
        return userService.getUserById(userId, include, jwt)
                .map(userRest -> ResponseEntity.status(HttpStatus.OK).body(userRest))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }

    @GetMapping
    public Flux<UserRest> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "limit", defaultValue = "50") int limit) {
        return userService.findAll(page, limit);
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UserRest> streamUsers() {
        /*
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> "Event " + sequence);
         */
        return userService.streamUser();
    }
}