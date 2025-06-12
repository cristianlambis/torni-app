package com.ralphdoe.torni.application.usecase;

import com.ralphdoe.torni.domain.model.Tournament;
import com.ralphdoe.torni.domain.model.TournamentType;
import com.ralphdoe.torni.domain.port.TournamentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class CreateTournamentUseCase {

    private static final Logger log = LoggerFactory.getLogger(CreateTournamentUseCase.class);
    private final TournamentRepository repository;

    public CreateTournamentUseCase(TournamentRepository repository) {
        this.repository = repository;
    }

    /**
     * Crea un nuevo Tournament y lo persiste.
     *
     * @param name        Nombre del torneo
     * @param type        Tipo (LEAGUE o KNOCKOUT)
     * @param playerCount Número de jugadores
     * @param startDate   Fecha de inicio
     * @return el Tournament recién creado
     */
    public Tournament execute(String name,
                              TournamentType type,
                              int playerCount,
                              LocalDate startDate) {
        log.info("Creating tournament: name={}, type={}, players={}, start={}",
                name, type, playerCount, startDate);
        Tournament t = new Tournament(name, type, playerCount, startDate);
        Tournament saved = repository.save(t);
        log.debug("Tournament created with id={}", saved.getId());
        return saved;
    }
}
