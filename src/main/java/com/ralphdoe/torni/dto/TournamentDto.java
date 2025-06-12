package com.ralphdoe.torni.dto;

import com.ralphdoe.torni.domain.model.TournamentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TournamentDto {
    private UUID id;
    private String name;
    private TournamentType type;
    private int playerCount;
    private LocalDate startDate;
}
