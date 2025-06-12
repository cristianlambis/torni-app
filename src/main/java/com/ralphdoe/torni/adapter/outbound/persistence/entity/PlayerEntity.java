package com.ralphdoe.torni.adapter.outbound.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "players")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PlayerEntity {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(columnDefinition = "uuid", nullable = false)
    private UUID tournamentId;

    private String name;

    private String avatarUrl;
}
