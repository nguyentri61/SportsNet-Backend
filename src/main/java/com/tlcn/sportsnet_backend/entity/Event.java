package com.tlcn.sportsnet_backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tlcn.sportsnet_backend.enums.EventStatusEnum;
import com.tlcn.sportsnet_backend.enums.EventTypeEnum;
import com.tlcn.sportsnet_backend.util.SecurityUtil;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String title;

    @Column(columnDefinition = "MEDIUMTEXT")
    String description;

    String coverImageUrl;
    String location;

    LocalDateTime startTime;
    LocalDateTime endTime;

    Integer capacity; // tối đa người/đội
    BigDecimal fee;

    boolean recurring;
    String recurrenceRule; // ví dụ: FREQ=WEEKLY;BYDAY=SA

    @Enumerated(EnumType.STRING)
    EventTypeEnum type;

    @Enumerated(EnumType.STRING)
    EventStatusEnum status;

    @ManyToOne @JoinColumn(name = "sport_id", nullable = false)
    Sport sport;

    @ManyToOne @JoinColumn(name = "club_id")
    Club club;

    @ManyToOne @JoinColumn(name = "organizer_id", nullable = false)
    Account organizer;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Team> teams = new HashSet<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Participant> participants = new HashSet<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Match> matches = new HashSet<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Payment> payments = new HashSet<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Notification> notifications = new HashSet<>();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    Instant createdAt;
    Instant updatedAt;
    String createdBy;
    String updatedBy;

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
        createdBy = SecurityUtil.getCurrentUserLogin().orElse("");
        status = status == null ? EventStatusEnum.DRAFT : status;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
        updatedBy = SecurityUtil.getCurrentUserLogin().orElse("");
    }
}
