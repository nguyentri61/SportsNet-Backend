package com.tlcn.sportsnet_backend.entity;

import com.tlcn.sportsnet_backend.enums.PaymentMethodEnum;
import com.tlcn.sportsnet_backend.enums.PaymentStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @OneToOne @JoinColumn(name = "participant_id", nullable = false, unique = true)
    Participant participant;

    @ManyToOne @JoinColumn(name = "event_id", nullable = false)
    Event event;

    BigDecimal amount;

    @Enumerated(EnumType.STRING)
    PaymentMethodEnum method; // CASH, BANK_TRANSFER, MOMO, VNPAY, STRIPE

    @Enumerated(EnumType.STRING)
    PaymentStatusEnum status; // PENDING, PAID, REFUNDED, FAILED

    String transactionCode;
    Instant paidAt;
}