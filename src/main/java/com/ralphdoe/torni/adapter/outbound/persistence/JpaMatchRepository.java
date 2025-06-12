package com.ralphdoe.torni.adapter.outbound.persistence;

import com.ralphdoe.torni.adapter.outbound.persistence.entity.MatchEntity;
import com.ralphdoe.torni.domain.model.Match;
import com.ralphdoe.torni.domain.port.MatchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class JpaMatchRepository implements MatchRepository {

    private final SpringDataMatchRepository repo;

    public JpaMatchRepository(SpringDataMatchRepository repo) {
        this.repo = repo;
    }

    @Override
    public Match save(Match m) {
        MatchEntity entity = new MatchEntity(
                m.getId(),
                m.getTournamentId(),
                m.getHomePlayerId(),
                m.getAwayPlayerId(),
                m.getHomeScore(),
                m.getAwayScore(),
                m.getScheduledAt()
        );
        entity = repo.save(entity);
        return toDomain(entity);
    }

    @Override
    public Optional<Match> findById(UUID id) {
        return repo.findById(id).map(this::toDomain);
    }

    @Override
    public List<Match> findByTournamentId(UUID tournamentId) {
        return repo.findByTournamentId(tournamentId)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        repo.deleteById(id);
    }

    private Match toDomain(MatchEntity e) {
        Match m = new Match(
                e.getTournamentId(),
                e.getHomePlayerId(),
                e.getAwayPlayerId(),
                e.getScheduledAt()
        );
        m.recordScore(e.getHomeScore(), e.getAwayScore());
        return m;
    }
}
