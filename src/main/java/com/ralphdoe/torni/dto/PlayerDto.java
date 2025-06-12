package com.ralphdoe.torni.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {
    private UUID id;
    private UUID tournamentId;
    private String name;
    private String avatarUrl;
}
