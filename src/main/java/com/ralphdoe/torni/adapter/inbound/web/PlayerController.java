package com.ralphdoe.torni.adapter.inbound.web;

import com.ralphdoe.torni.adapter.inbound.web.mapper.PlayerMapper;
import com.ralphdoe.torni.application.usecase.AddPlayerUseCase;
import com.ralphdoe.torni.domain.model.Player;
import com.ralphdoe.torni.domain.port.PlayerRepository;
import com.ralphdoe.torni.dto.CreatePlayerDto;
import com.ralphdoe.torni.dto.PlayerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tournaments/{tournamentId}/players")
public class PlayerController {

    private static final Logger log = LoggerFactory.getLogger(PlayerController.class);

    private final AddPlayerUseCase addPlayerUseCase;
    private final PlayerRepository playerRepo;
    private final PlayerMapper mapper;

    public PlayerController(AddPlayerUseCase addPlayerUseCase,
                            PlayerRepository playerRepo,
                            PlayerMapper mapper) {
        this.addPlayerUseCase = addPlayerUseCase;
        this.playerRepo       = playerRepo;
        this.mapper           = mapper;
    }

    @PostMapping
    public ResponseEntity<PlayerDto> addPlayer(@PathVariable UUID tournamentId,
                                               @RequestBody CreatePlayerDto dto) {
        log.info("Adding player to tournament {}: {}", tournamentId, dto);
        Player p = addPlayerUseCase.execute(
                tournamentId,
                dto.getName(),
                dto.getAvatarUrl()
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toDto(p));
    }

    @GetMapping
    public List<PlayerDto> listPlayers(@PathVariable UUID tournamentId) {
        return playerRepo.findByTournamentId(tournamentId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
