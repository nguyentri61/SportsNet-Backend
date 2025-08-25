package com.tlcn.sportsnet_backend.entity;

import com.tlcn.sportsnet_backend.enums.BadmintonCategoryEnum;
import com.tlcn.sportsnet_backend.enums.ClubVisibilityEnum;
import com.tlcn.sportsnet_backend.enums.EventStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "club_events")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClubEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String title;
    String description;

    String image;

    String location;
    LocalDate date;

    @Column(name = "start_time")
    LocalDateTime startTime;

    @Column(name = "end_time")
    LocalDateTime endTime;

    int totalMember;

    @Enumerated(EnumType.STRING)
    ClubVisibilityEnum clubVisibility; // public or private

    // Các hạng mục (Đơn nam, Đơn nữ, Đôi nam, ...)
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    List<BadmintonCategoryEnum> categories;

    @Enumerated(EnumType.STRING)
    EventStatusEnum status;

    // CLB tổ chức
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    Club club;

    @OneToMany(mappedBy = "clubEvent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClubEventParticipant> participants = new ArrayList<>();
}