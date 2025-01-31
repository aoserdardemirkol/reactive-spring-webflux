package com.reactive.ws.reactive_spring_webflux.presentation.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateUserRequest {

    @NotBlank(message="First name cannot be empty")
    @Size(min = 2, max = 50, message="First name cannot be shorter than 2 and longer than 50 characters")
    private String firstName;

    @NotBlank(message="Last name cannot be empty")
    @Size(min = 2, max = 50, message="Last name cannot be shorter than 2 and longer than 50 characters")
    private String lastName;

    @NotBlank(message="Email cannot be empty")
    @Email(message = "Please enter a valid email address")
    private String email;

    @NotBlank(message="Password cannot be empty")
    @Size(min = 8, max = 24, message="Password cannot be shorter than 8 and longer than 24 characters")
    private String password;
}