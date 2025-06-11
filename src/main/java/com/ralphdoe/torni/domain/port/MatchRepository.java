package com.ralphdoe.torni.domain.port;

import com.ralphdoe.torni.domain.model.Match;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MatchRepository {
    Match save(Match match);
    Optional<Match> findById(UUID id);
    List<Match> findByTournamentId(UUID tournamentId);
    void delete(UUID id);
}
