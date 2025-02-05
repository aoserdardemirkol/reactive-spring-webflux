package com.reactive.ws.photoalbums.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AlbumRest {

    private String userId;

    private UUID id;

    private String title;
}
