package com.ralphdoe.torni.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    /**
     * Constructor de rehidratación (public para que cualquier paquete pueda usarlo).
     */
    public Tournament(UUID id,
                      String name,
                      TournamentType type,
                      int playerCount,
                      LocalDate startDate) {
        // Validaciones
        if (id == null)
            throw new IllegalArgumentException("ID is required");
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name is required");
        if (playerCount < 2)
            throw new IllegalArgumentException("At least 2 players required");

        this.id          = id;
        this.name        = name;
        this.type        = type;
        this.playerCount = playerCount;
        this.startDate   = startDate;
    }

    /**
     * Constructor público para crear un nuevo torneo, genera UUID interno.
     */
    public Tournament(String name,
                      TournamentType type,
                      int playerCount,
                      LocalDate startDate) {
        this(UUID.randomUUID(), name, type, playerCount, startDate);
    }

    public void addPlayer(Player player) {
        if (players.size() >= playerCount) {
            throw new IllegalStateException("Tournament is full");
        }
        players.add(player);
    }
}
