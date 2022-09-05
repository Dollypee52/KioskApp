package com.semicolon.kioskApp.dtos.requests;

import lombok.Data;

@Data
public class CartRequestDto {
    private Long customerId;
    private Long productId;
    private int quantity;
}
