package com.reactive.ws.reactive_spring_webflux.presentation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRest {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;
}