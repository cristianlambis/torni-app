package com.ralphdoe.torni.domain.port;

import com.ralphdoe.torni.domain.model.Player;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlayerRepository {
    Player save(Player player);
    Optional<Player> findById(UUID id);
    List<Player> findByTournamentId(UUID tournamentId);
    void delete(UUID id);
}
