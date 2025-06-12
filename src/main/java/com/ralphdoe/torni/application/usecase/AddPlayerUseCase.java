package com.ralphdoe.torni.application.usecase;

import com.ralphdoe.torni.domain.model.Player;
import com.ralphdoe.torni.domain.model.Tournament;
import com.ralphdoe.torni.domain.port.PlayerRepository;
import com.ralphdoe.torni.domain.port.TournamentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class AddPlayerUseCase {

    private static final Logger log = LoggerFactory.getLogger(AddPlayerUseCase.class);
    private final TournamentRepository tournamentRepo;
    private final PlayerRepository playerRepo;

    public AddPlayerUseCase(TournamentRepository tournamentRepo,
                            PlayerRepository playerRepo) {
        this.tournamentRepo = tournamentRepo;
        this.playerRepo     = playerRepo;
    }

    /**
     * Crea un Player, lo asocia al Tournament (regla de negocio)
     * y lo persiste.
     *
     * @param tournamentId Id del torneo
     * @param name         Nombre del jugador
     * @param avatarUrl    URL del avatar (puede ser null)
     * @return el Player creado
     */
    public Player execute(UUID tournamentId,
                          String name,
                          String avatarUrl) {
        log.info("Adding player '{}' to tournament {}", name, tournamentId);
        Tournament t = tournamentRepo.findById(tournamentId)
                .orElseThrow(() -> new IllegalArgumentException("Tournament not found: " + tournamentId));

        Player p = new Player(t.getId(), name, avatarUrl);
        t.addPlayer(p);
        tournamentRepo.save(t);           // actualizar el torneo con el nuevo jugador
        Player saved = playerRepo.save(p); // persistir el jugador
        log.debug("Player created with id={}", saved.getId());
        return saved;
    }
}
