package com.reactive.ws.photoalbums.data;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Getter
@Setter
@Table(name = "album")
public class AlbumEntity {

    @Id
    private UUID id;

    @Column("userId")
    private String userId;

    @Column("title")
    private String title;
}
