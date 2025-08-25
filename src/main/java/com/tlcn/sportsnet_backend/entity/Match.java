package com.tlcn.sportsnet_backend.entity;

import com.tlcn.sportsnet_backend.enums.MatchStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.HashSet;

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

    String round; // Group, Quarter-Final, Semi-Final, Final, v.v.
    LocalDateTime startTime;

    @ManyToOne @JoinColumn(name = "court_id")
    Court court; // sân diễn ra trận

    @ManyToOne @JoinColumn(name = "team_a_id")
    Team teamA;

    @ManyToOne @JoinColumn(name = "team_b_id")
    Team teamB;

    Integer scoreA; // tổng set thắng (2/3)
    Integer scoreB;

    @ManyToOne @JoinColumn(name = "winner_team_id")
    Team winner;

    @Enumerated(EnumType.STRING)
    MatchStatusEnum status;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<MatchSet> sets = new HashSet<>();
}