package com.tlcn.sportsnet_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "match_sets")
public class MatchSet {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne @JoinColumn(name = "match_id", nullable = false)
    Match match;

    int setNumber; // 1, 2, 3
    int scoreA; // điểm team A trong set
    int scoreB; // điểm team B trong set
}