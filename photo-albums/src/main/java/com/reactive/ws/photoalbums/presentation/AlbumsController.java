package com.reactive.ws.photoalbums.presentation;

import com.reactive.ws.photoalbums.service.AlbumsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.security.Principal;

@RestController
@RequestMapping("/albums")
public class AlbumsController {

    private final AlbumsService albumsService;

    public AlbumsController(AlbumsService albumsService) {
        this.albumsService = albumsService;
    }

    @GetMapping
    public Flux<AlbumRest> getAlbums(Principal principal) {
        return albumsService.getAlbums(principal.getName())
                .filter(albumRest -> albumRest.getUserId().equals(principal.getName()));
    }

    @PostMapping
    public Mono<ResponseEntity<AlbumRest>> createAlbum(@Valid @RequestBody Mono<AlbumRest> album,
                                                       Principal principal) {
        return album.map(albumRest -> {
                    albumRest.setUserId(principal.getName());
                    return albumRest;
                }).flatMap(albumRest -> albumsService.createAlbum(Mono.just(albumRest)))
                .map(albumRest -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .location(URI.create("/albums/" + albumRest.getId()))
                        .body(albumRest));
    }
}
