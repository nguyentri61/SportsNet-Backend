package com.tlcn.sportsnet_backend.entity;

import com.tlcn.sportsnet_backend.enums.MatchStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne @JoinColumn(name = "event_id", nullable = false)
    Event event;

    String round; // Group Stage, Quarter-Final, Semi-Final, Final
    LocalDateTime startTime;
    String location; // "Sân số 1"

    @ManyToOne @JoinColumn(name = "team_a_id")
    Team teamA;

    @ManyToOne @JoinColumn(name = "team_b_id")
    Team teamB;

    Integer scoreA;
    Integer scoreB;

    @Enumerated(EnumType.STRING)
    MatchStatusEnum status;
}