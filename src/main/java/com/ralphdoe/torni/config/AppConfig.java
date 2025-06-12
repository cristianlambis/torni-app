package com.ralphdoe.torni.config;

import com.ralphdoe.torni.application.usecase.*;
import com.ralphdoe.torni.domain.port.MatchRepository;
import com.ralphdoe.torni.domain.port.PlayerRepository;
import com.ralphdoe.torni.domain.port.TournamentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public CreateTournamentUseCase createTournamentUseCase(TournamentRepository tournamentRepo) {
        return new CreateTournamentUseCase(tournamentRepo);
    }

    @Bean
    public AddPlayerUseCase addPlayerUseCase(TournamentRepository tournamentRepo,
                                             PlayerRepository playerRepo) {
        return new AddPlayerUseCase(tournamentRepo, playerRepo);
    }

    @Bean
    public GenerateFixtureUseCase generateFixtureUseCase(TournamentRepository tournamentRepo,
                                                         MatchRepository matchRepo) {
        return new GenerateFixtureUseCase(tournamentRepo, matchRepo);
    }

    @Bean
    public RecordResultUseCase recordResultUseCase(MatchRepository matchRepo) {
        return new RecordResultUseCase(matchRepo);
    }
}
