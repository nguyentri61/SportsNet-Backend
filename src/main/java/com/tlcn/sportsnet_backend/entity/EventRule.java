package com.tlcn.sportsnet_backend.entity;

import com.tlcn.sportsnet_backend.enums.EventTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "event_rules")
public class EventRule {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Enumerated(EnumType.STRING)
    EventTypeEnum activityType; // TOURNAMENT, TRAINING, FRIENDLY, WEEKLY

    @Column(columnDefinition="TEXT")
    String description;  // Mô tả quy tắc

    Integer minParticipants;
    Integer maxParticipants;
    Integer teamSize; // số người mỗi đội (nếu có)

    @ManyToOne
    @JoinColumn(name = "sport_type_id", nullable = false)
    SportType sportType;
}