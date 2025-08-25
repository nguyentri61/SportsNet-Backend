package com.tlcn.sportsnet_backend.entity;

import com.tlcn.sportsnet_backend.enums.CourtStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.math.BigDecimal;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "courts")
public class Court {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(nullable = false)
    String name; // "Sân 1", "Sân 2"...

    @ManyToOne
    @JoinColumn(name = "facility_id", nullable = false)
    Facility facility;


    @Enumerated(EnumType.STRING)
    CourtStatusEnum status; // AVAILABLE, MAINTENANCE, INACTIVE

    BigDecimal pricePerHour; // giá/giờ
}