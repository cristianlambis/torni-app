package com.ralphdoe.torni.application.usecase;

import com.ralphdoe.torni.domain.model.Match;
import com.ralphdoe.torni.domain.port.MatchRepository;
import com.ralphdoe.torni.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class RecordResultUseCase {

    private static final Logger log = LoggerFactory.getLogger(RecordResultUseCase.class);
    private final MatchRepository matchRepo;

    public RecordResultUseCase(MatchRepository matchRepo) {
        this.matchRepo = matchRepo;
    }

    /**
     * Actualiza el marcador de un Match existente.
     *
     * @param matchId   Id del partido
     * @param homeScore Goles del local
     * @param awayScore Goles del visitante
     * @return el Match actualizado
     */
    public Match execute(UUID matchId, int homeScore, int awayScore) {
        log.info("Recording result for match {}: {}-{}", matchId, homeScore, awayScore);
        Match m = matchRepo.findById(matchId)
                .orElseThrow(() -> new ResourceNotFoundException("Match not found: " + matchId));

        m.recordScore(homeScore, awayScore);
        Match updated = matchRepo.save(m);
        log.debug("Result recorded for match {}", updated.getId());
        return updated;
    }
}
