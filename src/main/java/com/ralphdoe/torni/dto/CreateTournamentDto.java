package com.ralphdoe.torni.dto;

import com.ralphdoe.torni.domain.model.TournamentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTournamentDto {
    private String name;
    private TournamentType type;
    private int playerCount;
    private LocalDate startDate;
}
