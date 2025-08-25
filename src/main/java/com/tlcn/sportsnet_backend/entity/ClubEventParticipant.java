package com.tlcn.sportsnet_backend.entity;

import com.tlcn.sportsnet_backend.enums.ParticipantStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Entity
@Table(name = "club_event_participants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClubEventParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    boolean isClubMember;

    @Enumerated(EnumType.STRING)
    ParticipantStatusEnum status; // PENDING, CONFIRMED, REJECTED

    Instant joinedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_event_id", nullable = false)
    ClubEvent clubEvent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    Account participant;
}