package com.sample.wallet.business;

import com.sample.wallet.Exception.InvalidUserException;
import com.sample.wallet.entity.Transaction;
import com.sample.wallet.entity.Wallet;
import com.sample.wallet.repository.TransactionRepository;
import com.sample.wallet.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Slf4j
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public BigDecimal getBalance(int userId) throws InvalidUserException {

        BigDecimal balance = walletRepository.getBalance(userId);

        if (log.isDebugEnabled())
            log.debug("the balance for this user {} is {} .",userId,balance);

        if (balance == null )
            throw new InvalidUserException();

        return balance; // Placeholder
    }

    @Transactional
    public long changeBalance(int userId, int amount) throws InvalidUserException {

        Wallet wallet = walletRepository.getWalletByUser_id(userId);

        if (log.isDebugEnabled())
            log.debug("the wallet for this user {} is {} .",userId,wallet);


        if (wallet == null)
            throw new InvalidUserException();

        Transaction transaction = transactionRepository.save(new Transaction(wallet.getId(), amount, LocalDateTime.now()));

        if (log.isDebugEnabled())
            log.debug("the created transaction is {} .",transaction);


        return transaction.getId();
    }
}