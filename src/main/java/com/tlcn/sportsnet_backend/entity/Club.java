package com.tlcn.sportsnet_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
@Table(name = "clubs")
public class Club {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    String id;

    String name;

    @Column(columnDefinition = "MEDIUMTEXT")
    String description;

    String logoUrl;
    Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    Account owner;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<ClubMember> members = new HashSet<>();

    @OneToMany(mappedBy = "club")
    Set<Event> events = new HashSet<>();

    @OneToMany(mappedBy = "club")
    Set<Post> posts = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
    }
}
