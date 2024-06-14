package com.sample.wallet.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddMoneyResponse {
    private long referenceId;
}
