package com.tlcn.sportsnet_backend.entity;

import com.tlcn.sportsnet_backend.enums.FriendStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "friendships")
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Enumerated(EnumType.STRING)
    FriendStatusEnum status; // PENDING, ACCEPTED, BLOCKED

    Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "requester_id", nullable = false)
    Account requester;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    Account receiver;

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
    }
}
