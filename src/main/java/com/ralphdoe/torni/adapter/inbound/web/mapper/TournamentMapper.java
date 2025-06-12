package com.ralphdoe.torni.adapter.inbound.web.mapper;

import com.ralphdoe.torni.domain.model.Tournament;
import com.ralphdoe.torni.dto.CreateTournamentDto;
import com.ralphdoe.torni.dto.TournamentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TournamentMapper {

    // Domain → DTO
    TournamentDto toDto(Tournament tournament);

    // DTO → Domain: ignoramos el ID, que se genera internamente
    @Mapping(target = "id", ignore = true)
    Tournament toDomain(CreateTournamentDto dto);
}
