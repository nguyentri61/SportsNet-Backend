package com.tlcn.sportsnet_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "sport_types")
public class SportType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String name;
    String iconUrl;
}
