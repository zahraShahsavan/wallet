package com.sample.wallet.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class GetBalanceResponse {
    private BigDecimal balance;
}
