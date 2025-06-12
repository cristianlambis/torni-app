package com.ralphdoe.torni.application.usecase;

import com.ralphdoe.torni.domain.model.Match;
import com.ralphdoe.torni.domain.model.Tournament;
import com.ralphdoe.torni.domain.model.TournamentType;
import com.ralphdoe.torni.domain.port.MatchRepository;
import com.ralphdoe.torni.domain.port.TournamentRepository;
import com.ralphdoe.torni.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class GenerateFixtureUseCase {

    private static final Logger log = LoggerFactory.getLogger(GenerateFixtureUseCase.class);
    private final TournamentRepository tournamentRepo;
    private final MatchRepository matchRepo;

    public GenerateFixtureUseCase(TournamentRepository tournamentRepo,
                                  MatchRepository matchRepo) {
        this.tournamentRepo = tournamentRepo;
        this.matchRepo      = matchRepo;
    }

    /**
     * Genera el fixture según el tipo de torneo y persiste los partidos.
     *
     * @param tournamentId Id del torneo
     * @return lista de Match generados
     */
    public List<Match> execute(UUID tournamentId) {
        log.info("Generating fixture for tournament {}", tournamentId);
        Tournament t = tournamentRepo.findById(tournamentId)
                .orElseThrow(() -> new ResourceNotFoundException("Tournament not found: " + tournamentId));

        // Limpia partidos antiguos
        // (suponiendo que adapter outbound entenderá delete por torneo)
        // matchRepo.deleteByTournamentId(tournamentId);

        List<Match> generated;
        if (t.getType() == TournamentType.LEAGUE) {
            generated = generateLeagueMatches(t);
        } else {
            generated = generateKnockoutMatches(t);
        }

        generated.forEach(matchRepo::save);
        log.debug("Generated {} matches", generated.size());
        return generated;
    }

    private List<Match> generateLeagueMatches(Tournament t) {
        List<Match> list = new java.util.ArrayList<>();
        var players = t.getPlayers();
        for (int i = 0; i < players.size(); i++) {
            for (int j = i + 1; j < players.size(); j++) {
                // programar en fecha actual + i días (ejemplo)
                var when = LocalDateTime.now().plusDays(i);
                list.add(new Match(t.getId(),
                        players.get(i).getId(),
                        players.get(j).getId(),
                        when));
            }
        }
        return list;
    }

    private List<Match> generateKnockoutMatches(Tournament t) {
        List<Match> list = new java.util.ArrayList<>();
        var players = t.getPlayers();
        for (int i = 0; i < players.size(); i += 2) {
            var when = LocalDateTime.now().plusDays(i);
            list.add(new Match(t.getId(),
                    players.get(i).getId(),
                    players.get(i + 1).getId(),
                    when));
        }
        return list;
    }
}
