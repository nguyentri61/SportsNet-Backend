package com.tlcn.sportsnet_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    String id;

    String fullName;
    LocalDate birthDate;
    String gender;
    String address;
    String bio;
    String avatarUrl;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false)
    Account account;
}
