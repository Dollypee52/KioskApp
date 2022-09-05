package com.semicolon.kioskApp.dtos.responses;

import com.semicolon.kioskApp.model.Item;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CartResponseDto {
    private List<Item> itemList;
    private double totalPrice;
}
