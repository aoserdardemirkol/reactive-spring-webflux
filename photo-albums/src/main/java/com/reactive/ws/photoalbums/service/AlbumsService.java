package com.reactive.ws.photoalbums.service;

import com.reactive.ws.photoalbums.presentation.AlbumRest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface AlbumsService {

    Flux<AlbumRest> getAlbums(String userId);

    Mono<AlbumRest> createAlbum(Mono<AlbumRest> album);

    Mono<AlbumRest> getAlbum(UUID id, String userId);

    Mono<AlbumRest> updateAlbum(Mono<AlbumRest> album);

    Mono<Void> deleteAlbum(UUID id, String userId);
}
