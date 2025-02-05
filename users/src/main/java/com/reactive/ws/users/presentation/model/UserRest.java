package com.reactive.ws.users.presentation.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<AlbumRest> albums;
}