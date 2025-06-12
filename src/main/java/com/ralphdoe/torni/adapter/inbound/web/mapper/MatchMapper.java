package com.ralphdoe.torni.adapter.inbound.web.mapper;

import com.ralphdoe.torni.domain.model.Match;
import com.ralphdoe.torni.dto.MatchDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MatchMapper {

    // Domain → DTO
    MatchDto toDto(Match match);
}
