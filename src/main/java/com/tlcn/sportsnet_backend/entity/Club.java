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
    @JoinColumn(name="owner_id")
    private Account owner;

    @ManyToMany
    @JoinTable(name="club_members",
            joinColumns=@JoinColumn(name="club_id"),
            inverseJoinColumns=@JoinColumn(name="account_id"))
    private Set<Account> members = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
    }
}
