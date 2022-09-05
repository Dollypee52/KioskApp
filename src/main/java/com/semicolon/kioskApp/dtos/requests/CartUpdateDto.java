package com.semicolon.kioskApp.dtos.requests;

import com.semicolon.kioskApp.dtos.responses.QuantityOperation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartUpdateDto {
    private Long customerId;
    private Long itemId;
    private QuantityOperation quantityOp;
}
