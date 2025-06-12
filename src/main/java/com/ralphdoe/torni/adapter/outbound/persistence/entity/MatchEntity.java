package com.ralphdoe.torni.adapter.outbound.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "matches")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MatchEntity {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(columnDefinition = "uuid", nullable = false)
    private UUID tournamentId;

    @Column(columnDefinition = "uuid", nullable = false)
    private UUID homePlayerId;

    @Column(columnDefinition = "uuid", nullable = false)
    private UUID awayPlayerId;

    private int homeScore;

    private int awayScore;

    private LocalDateTime scheduledAt;
}
