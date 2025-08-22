package com.tlcn.sportsnet_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String content;
    LocalDateTime sentAt;
    Boolean seen;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    Account sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    Account receiver; // private chat (null nếu group chat)

    @ManyToOne
    @JoinColumn(name = "chat_group_id")
    ChatGroup chatGroup; // null nếu private chat
}
