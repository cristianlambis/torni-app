package com.ralphdoe.torni.adapter.outbound.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tournaments")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TournamentEntity {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private String type;

    private int playerCount;

    private LocalDate startDate;
}
