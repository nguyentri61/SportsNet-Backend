package com.tlcn.sportsnet_backend.entity;

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
@Table(name = "event_participants")
public class EventParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String teamName; // nếu có đội
    String role;     // PLAYER, REFEREE, COACH, ORGANIZER
    LocalDateTime joinedAt;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    Account account;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    Event event;
}
