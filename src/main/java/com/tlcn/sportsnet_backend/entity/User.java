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
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    String id;

    String fullName;
    String phone;
    String avatarUrl;
    String bio;

    @OneToOne @JoinColumn(name="account_id", unique = true)
    Account account;
}
