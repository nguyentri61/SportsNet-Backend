package com.tlcn.sportsnet_backend.entity;

import com.tlcn.sportsnet_backend.enums.TeamTypeEnum;
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

    @Enumerated(EnumType.STRING)
    TeamTypeEnum teamType; // SINGLE/DOUBLE

    @ManyToOne @JoinColumn(name = "event_id", nullable = false)
    Event event; // team thuộc event nào

    @ManyToOne @JoinColumn(name = "captain_id")
    Account captain;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Participant> members = new HashSet<>();
}
