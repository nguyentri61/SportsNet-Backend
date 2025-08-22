package com.tlcn.sportsnet_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String name;

    @ManyToOne @JoinColumn(name = "sport_id", nullable = false)
    Sport sport;

    @ManyToOne @JoinColumn(name = "event_id", nullable = false)
    Event event; // team thuộc event nào

    @ManyToOne @JoinColumn(name = "captain_id")
    Account captain;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Participant> members = new HashSet<>();
}
