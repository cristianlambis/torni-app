package com.ralphdoe.torni.adapter.outbound.persistence;

import com.ralphdoe.torni.adapter.outbound.persistence.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataPlayerRepository extends JpaRepository<PlayerEntity, UUID> {
    List<PlayerEntity> findByTournamentId(UUID tournamentId);
}
