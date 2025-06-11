package com.ralphdoe.torni.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode(of = "id")
public class Tournament {

    private final UUID id;
    private String name;
    private TournamentType type;
    private int playerCount;
    private LocalDate startDate;
    private final List<Player> players = new ArrayList<>();
    private final List<Match> matches  = new ArrayList<>();

    public Tournament(String name, TournamentType type, int playerCount, LocalDate startDate) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name is required");
        if (playerCount < 2)
            throw new IllegalArgumentException("At least 2 players required");
        this.id          = UUID.randomUUID();
        this.name        = name;
        this.type        = type;
        this.playerCount = playerCount;
        this.startDate   = startDate;
    }

    public void addPlayer(Player player) {
        if (players.size() >= playerCount) {
            throw new IllegalStateException("Tournament is full");
        }
        players.add(player);
    }
}

