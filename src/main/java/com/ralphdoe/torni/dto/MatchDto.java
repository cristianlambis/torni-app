package com.ralphdoe.torni.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchDto {
    private UUID id;
    private UUID tournamentId;
    private UUID homePlayerId;
    private UUID awayPlayerId;
    private int homeScore;
    private int awayScore;
    private LocalDateTime scheduledAt;
}
