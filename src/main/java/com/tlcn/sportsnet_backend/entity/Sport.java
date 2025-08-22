package com.tlcn.sportsnet_backend.entity;

import com.tlcn.sportsnet_backend.enums.SportTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "sports")
public class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String name; // "Cầu lông", "Bóng đá", ...

    @Enumerated(EnumType.STRING)
    SportTypeEnum type; // INDIVIDUAL / TEAM

    @Column(columnDefinition = "json")
    String defaultRules;

    @OneToMany(mappedBy = "sport")
    List<Event> events;
}
