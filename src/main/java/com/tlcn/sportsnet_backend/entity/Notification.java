package com.tlcn.sportsnet_backend.entity;

import com.tlcn.sportsnet_backend.enums.NotificationTypeEnum;
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

    String title;

    @Column(columnDefinition = "MEDIUMTEXT")
    String content;

    @Enumerated(EnumType.STRING)
    NotificationTypeEnum type; // ANNOUNCEMENT, REMINDER, RESULT, CHANGE_SCHEDULE

    Instant createdAt;

    @ManyToOne @JoinColumn(name = "account_id", nullable = false)
    Account account;

    @ManyToOne @JoinColumn(name = "event_id")
    Event event;

    @PrePersist
    protected void onCreate() { createdAt = Instant.now(); }
}
