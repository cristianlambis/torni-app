package com.ralphdoe.torni.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode(of = "id")
public class Match {

    private final UUID id;
    private final UUID tournamentId;
    private final UUID homePlayerId;
    private final UUID awayPlayerId;
    private int homeScore;
    private int awayScore;
    private LocalDateTime scheduledAt;

    public Match(UUID tournamentId, UUID homePlayerId, UUID awayPlayerId, LocalDateTime scheduledAt) {
        this.id             = UUID.randomUUID();
        this.tournamentId   = tournamentId;
        this.homePlayerId   = homePlayerId;
        this.awayPlayerId   = awayPlayerId;
        this.scheduledAt    = scheduledAt;
    }

    public void recordScore(int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Scores cannot be negative");
        }
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }
}
