package com.ralphdoe.torni.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode(of = "id")
public class Player {

    private final UUID id;
    private final UUID tournamentId;
    private String name;
    private String avatarUrl;

    public Player(UUID tournamentId, String name, String avatarUrl) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name is required");
        this.id           = UUID.randomUUID();
        this.tournamentId = tournamentId;
        this.name         = name;
        this.avatarUrl    = avatarUrl;
    }
}
