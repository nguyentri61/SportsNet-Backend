package com.tlcn.sportsnet_backend.entity;

import com.tlcn.sportsnet_backend.enums.ParticipantRoleEnum;
import com.tlcn.sportsnet_backend.enums.ParticipantStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "participants")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne @JoinColumn(name = "user_id", nullable = false)
    Account user;

    @ManyToOne @JoinColumn(name = "event_id", nullable = false)
    Event event;

    @ManyToOne @JoinColumn(name = "team_id")
    Team team; // nullable nếu cá nhân

    @Enumerated(EnumType.STRING)
    ParticipantRoleEnum role; // PLAYER, COACH, REFEREE, MANAGER

    @Enumerated(EnumType.STRING)
    ParticipantStatusEnum status; // PENDING, CONFIRMED, CHECKED_IN, CANCELLED

    Instant joinedAt;

    @OneToOne(mappedBy = "participant", cascade = CascadeType.ALL, orphanRemoval = true)
    Payment payment;
}
