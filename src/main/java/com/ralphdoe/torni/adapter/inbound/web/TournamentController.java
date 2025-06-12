package com.ralphdoe.torni.adapter.inbound.web;

import com.ralphdoe.torni.adapter.inbound.web.mapper.TournamentMapper;
import com.ralphdoe.torni.application.usecase.CreateTournamentUseCase;
import com.ralphdoe.torni.domain.model.Tournament;
import com.ralphdoe.torni.domain.model.TournamentType;
import com.ralphdoe.torni.domain.port.TournamentRepository;
import com.ralphdoe.torni.dto.CreateTournamentDto;
import com.ralphdoe.torni.dto.TournamentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    private static final Logger log = LoggerFactory.getLogger(TournamentController.class);

    private final CreateTournamentUseCase createUseCase;
    private final TournamentRepository tournamentRepo;
    private final TournamentMapper mapper;

    public TournamentController(CreateTournamentUseCase createUseCase,
                                TournamentRepository tournamentRepo,
                                TournamentMapper mapper) {
        this.createUseCase   = createUseCase;
        this.tournamentRepo  = tournamentRepo;
        this.mapper          = mapper;
    }

    @PostMapping
    public ResponseEntity<TournamentDto> create(@RequestBody CreateTournamentDto dto) {
        log.info("Received request to create tournament: {}", dto);
        Tournament t = createUseCase.execute(
                dto.getName(),
                dto.getType(),
                dto.getPlayerCount(),
                dto.getStartDate()
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toDto(t));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TournamentDto> getById(@PathVariable UUID id) {
        return tournamentRepo.findById(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<TournamentDto> getAll() {
        return tournamentRepo.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
