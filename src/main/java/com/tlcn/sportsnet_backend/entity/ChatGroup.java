package com.tlcn.sportsnet_backend.entity;

import com.tlcn.sportsnet_backend.enums.ChatGroupTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "chat_groups")
public class ChatGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String name;

    @Enumerated(EnumType.STRING)
    ChatGroupTypeEnum type; // PRIVATE, GROUP, CLUB
    String avatarUrl;
    Instant createdAt;

    @OneToMany(mappedBy = "chatGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<GroupMember> members = new HashSet<>();

    @OneToMany(mappedBy = "chatGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Message> messages = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
    }
}
