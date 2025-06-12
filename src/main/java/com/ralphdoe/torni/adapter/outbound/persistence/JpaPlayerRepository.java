package com.ralphdoe.torni.adapter.outbound.persistence;

import com.ralphdoe.torni.adapter.outbound.persistence.entity.PlayerEntity;
import com.ralphdoe.torni.domain.model.Player;
import com.ralphdoe.torni.domain.port.PlayerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class JpaPlayerRepository implements PlayerRepository {

    private final SpringDataPlayerRepository repo;

    public JpaPlayerRepository(SpringDataPlayerRepository repo) {
        this.repo = repo;
    }

    @Override
    public Player save(Player p) {
        PlayerEntity entity = new PlayerEntity(
                p.getId(),
                p.getTournamentId(),
                p.getName(),
                p.getAvatarUrl()
        );
        entity = repo.save(entity);
        return toDomain(entity);
    }

    @Override
    public Optional<Player> findById(UUID id) {
        return repo.findById(id).map(this::toDomain);
    }

    @Override
    public List<Player> findByTournamentId(UUID tournamentId) {
        return repo.findByTournamentId(tournamentId)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        repo.deleteById(id);
    }

    private Player toDomain(PlayerEntity e) {
        return new Player(e.getTournamentId(), e.getName(), e.getAvatarUrl());
    }
}
