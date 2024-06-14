package com.sample.wallet.controller;


import com.sample.wallet.Exception.InvalidUserException;
import com.sample.wallet.business.WalletService;
import com.sample.wallet.dto.response.AddMoneyResponse;
import com.sample.wallet.dto.response.GetBalanceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/get-balance")
    public ResponseEntity<GetBalanceResponse> getBalance(@RequestParam int user_id) throws InvalidUserException {
        BigDecimal balance = walletService.getBalance(user_id);
        GetBalanceResponse response = GetBalanceResponse.builder().balance(balance).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add-money")
    public ResponseEntity<AddMoneyResponse> addMoney(@RequestParam int user_id, @RequestParam int amount) throws InvalidUserException {
        long referenceId = walletService.changeBalance(user_id, amount);
        AddMoneyResponse response = AddMoneyResponse.builder().referenceId(referenceId).build();
        return ResponseEntity.ok(response);
    }
}