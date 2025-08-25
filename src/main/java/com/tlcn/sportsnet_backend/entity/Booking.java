package com.tlcn.sportsnet_backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tlcn.sportsnet_backend.enums.BookingStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;


@Entity
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "bookings")
public class Booking {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    String id;


    @ManyToOne @JoinColumn(name = "court_id", nullable = false)
    Court court;


    @ManyToOne @JoinColumn(name = "user_id", nullable = false)
    Account user;


    // Nếu booking gắn với Event (đặt sân cho sự kiện)
    @ManyToOne @JoinColumn(name = "event_id")
    Event event;


    LocalDateTime startTime;
    LocalDateTime endTime;


    BigDecimal totalPrice;


    @Enumerated(EnumType.STRING)
    BookingStatusEnum status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    Instant createdAt;


    @PrePersist
    public void prePersist() { createdAt = Instant.now(); }
}