package com.sample.wallet.repository;

import com.sample.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;


@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    @Query("SELECT w.balance FROM Wallet w WHERE w.user_id = :userId")
    BigDecimal getBalance(int userId);
    @Query("SELECT w FROM Wallet w WHERE w.user_id = :userId")
    Wallet getWalletByUser_id(int userId);
}
