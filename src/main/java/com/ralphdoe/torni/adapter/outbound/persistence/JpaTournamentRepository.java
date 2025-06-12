package com.ralphdoe.torni.adapter.outbound.persistence;

import com.ralphdoe.torni.adapter.outbound.persistence.entity.TournamentEntity;
import com.ralphdoe.torni.domain.model.Tournament;
import com.ralphdoe.torni.domain.model.TournamentType;
import com.ralphdoe.torni.domain.port.TournamentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class JpaTournamentRepository implements TournamentRepository {

    private final SpringDataTournamentRepository repo;

    public JpaTournamentRepository(SpringDataTournamentRepository repo) {
        this.repo = repo;
    }

    @Override
    public Tournament save(Tournament t) {
        TournamentEntity entity = new TournamentEntity(
                t.getId(),
                t.getName(),
                t.getType().name(),
                t.getPlayerCount(),
                t.getStartDate()
        );
        entity = repo.save(entity);
        return toDomain(entity);
    }

    @Override
    public Optional<Tournament> findById(UUID id) {
        return repo.findById(id).map(this::toDomain);
    }

    @Override
    public List<Tournament> findAll() {
        return repo.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        repo.deleteById(id);
    }

    private Tournament toDomain(TournamentEntity e) {
        return new Tournament(
                e.getName(),
                TournamentType.valueOf(e.getType()),
                e.getPlayerCount(),
                e.getStartDate()
        );
    }
}
