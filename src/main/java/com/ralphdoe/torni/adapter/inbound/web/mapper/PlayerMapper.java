package com.ralphdoe.torni.adapter.inbound.web.mapper;

import com.ralphdoe.torni.domain.model.Player;
import com.ralphdoe.torni.dto.CreatePlayerDto;
import com.ralphdoe.torni.dto.PlayerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    // Domain → DTO
    PlayerDto toDto(Player player);

    // Construye un Player a partir de CreatePlayerDto y un tournamentId
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tournamentId", source = "tournamentId")
    Player toDomain(UUID tournamentId, CreatePlayerDto dto);
}
