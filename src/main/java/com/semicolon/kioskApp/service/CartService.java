package com.semicolon.kioskApp.service;

import com.semicolon.kioskApp.dtos.requests.CartRequestDto;
import com.semicolon.kioskApp.dtos.requests.CartUpdateDto;
import com.semicolon.kioskApp.dtos.responses.CartResponseDto;
import com.semicolon.kioskApp.exception.KioskException;

public interface CartService {

    CartResponseDto addItemToCart(CartRequestDto requestDto) throws KioskException;

    CartResponseDto updateCartItem(CartUpdateDto cartUpdateDto) throws KioskException;
}
