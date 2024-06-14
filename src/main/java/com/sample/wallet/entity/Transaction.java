package com.sample.wallet.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long walletId;

    @Column
    private int amount;

    @Column
    private LocalDateTime timestamp;

    public Transaction(long walletId, int amount, LocalDateTime timestamp) {
        this.walletId = walletId;
        this.amount = amount;
        this.timestamp = timestamp;
    }
}