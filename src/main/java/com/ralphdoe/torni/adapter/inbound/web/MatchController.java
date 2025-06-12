package com.ralphdoe.torni.adapter.inbound.web;

import com.ralphdoe.torni.adapter.inbound.web.mapper.MatchMapper;
import com.ralphdoe.torni.application.usecase.GenerateFixtureUseCase;
import com.ralphdoe.torni.application.usecase.RecordResultUseCase;
import com.ralphdoe.torni.domain.model.Match;
import com.ralphdoe.torni.domain.port.MatchRepository;
import com.ralphdoe.torni.dto.MatchDto;
import com.ralphdoe.torni.dto.RecordResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MatchController {

    private static final Logger log = LoggerFactory.getLogger(MatchController.class);

    private final GenerateFixtureUseCase generateFixtureUseCase;
    private final RecordResultUseCase recordResultUseCase;
    private final MatchRepository matchRepo;
    private final MatchMapper mapper;

    public MatchController(GenerateFixtureUseCase generateFixtureUseCase,
                           RecordResultUseCase recordResultUseCase,
                           MatchRepository matchRepo,
                           MatchMapper mapper) {
        this.generateFixtureUseCase = generateFixtureUseCase;
        this.recordResultUseCase    = recordResultUseCase;
        this.matchRepo              = matchRepo;
        this.mapper                 = mapper;
    }

    @PostMapping("/tournaments/{tournamentId}/fixture")
    public List<MatchDto> generateFixture(@PathVariable UUID tournamentId) {
        log.info("Generating fixture for tournament {}", tournamentId);
        List<Match> matches = generateFixtureUseCase.execute(tournamentId);
        return matches.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/tournaments/{tournamentId}/matches")
    public List<MatchDto> listMatches(@PathVariable UUID tournamentId) {
        return matchRepo.findByTournamentId(tournamentId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/matches/{matchId}/result")
    public ResponseEntity<MatchDto> recordResult(@PathVariable UUID matchId,
                                                 @RequestBody RecordResultDto dto) {
        log.info("Recording result for match {}: {}-{}", matchId, dto.getHomeScore(), dto.getAwayScore());
        Match updated = recordResultUseCase.execute(
                matchId,
                dto.getHomeScore(),
                dto.getAwayScore()
        );
        return ResponseEntity.ok(mapper.toDto(updated));
    }
}
