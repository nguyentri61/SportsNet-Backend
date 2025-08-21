package com.tlcn.sportsnet_backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tlcn.sportsnet_backend.enums.EventTypeEnum;
import com.tlcn.sportsnet_backend.enums.PaymentMethodEnum;
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

    @Column(columnDefinition="MEDIUMTEXT")
    String description;

    String coverImageUrl;
    LocalDateTime startTime;
    LocalDateTime endTime;
    String location;
    String status;
    BigDecimal fee;

    @Enumerated(EnumType.STRING)
    PaymentMethodEnum paymentMethod;

    @Enumerated(EnumType.STRING)
    EventTypeEnum type; // Giải đấu / Huấn luyện / Giao hữu

    @ManyToOne
    @JoinColumn(name = "sport_type_id", nullable = false)
    SportType sportType;

    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = false)
    Account organizer;

    @ManyToOne
    @JoinColumn(name = "rule_id")
    EventRule rule; // Quy tắc áp dụng

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<EventParticipant> participants = new HashSet<>();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss a", timezone = "GMT+7")
    Instant createdAt;

    Instant updatedAt;

    String createdBy;

    String updatedBy;

    @PrePersist
    public void handleBeforeCreate(){
        createdAt = Instant.now();
        createdBy = SecurityUtil.getCurrentUserLogin().isPresent()
                ? SecurityUtil.getCurrentUserLogin().get()
                : "";
    }

    @PreUpdate
    public void handleBeforeUpdate(){
        updatedAt = Instant.now();
        updatedBy = SecurityUtil.getCurrentUserLogin().isPresent()
                ? SecurityUtil.getCurrentUserLogin().get()
                : "";
    }
}
