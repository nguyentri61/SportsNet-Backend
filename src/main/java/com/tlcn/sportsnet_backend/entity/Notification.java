package com.tlcn.sportsnet_backend.entity;

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
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne
    @JoinColumn(name="receiver_id")
    Account receiver;

    String type;     // EVENT_INVITE, COMMENT, LIKE, FRIEND_REQUEST, ...
    String message;
    String link;     // "/events/123"
    boolean isRead;
    Instant createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
        isRead = false;
    }
}
