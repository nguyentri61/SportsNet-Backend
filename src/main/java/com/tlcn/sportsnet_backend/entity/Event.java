package com.tlcn.sportsnet_backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tlcn.sportsnet_backend.enums.PaymentMethodEnum;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss a", timezone = "GMT+7")
    Instant createdAt;

    Instant updatedAt;

    String createdBy;

    String updatedBy;

    @PrePersist
    public void handleBeforeCreate(){
        createdAt = Instant.now();
//        createdBy = SecurityUtil.getCurrentUserLogin().isPresent()
//                ? SecurityUtil.getCurrentUserLogin().get()
//                : "";
    }

    @PreUpdate
    public void handleBeforeUpdate(){
        updatedAt = Instant.now();
//        updatedBy = SecurityUtil.getCurrentUserLogin().isPresent()
//                ? SecurityUtil.getCurrentUserLogin().get()
//                : "";
    }

    @ManyToOne
    @JoinColumn(name = "sport_type_id")
    SportType sportType;

    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = false)
    Account organizer;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<EventParticipant> participants = new HashSet<>();
}
