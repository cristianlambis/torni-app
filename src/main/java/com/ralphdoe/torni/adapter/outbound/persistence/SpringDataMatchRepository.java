package com.ralphdoe.torni.adapter.outbound.persistence;

import com.ralphdoe.torni.adapter.outbound.persistence.entity.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataMatchRepository extends JpaRepository<MatchEntity, UUID> {
    List<MatchEntity> findByTournamentId(UUID tournamentId);
}
