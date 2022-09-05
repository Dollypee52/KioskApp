package com.semicolon.kioskApp.controller;

import com.semicolon.kioskApp.dtos.requests.CartRequestDto;
import com.semicolon.kioskApp.dtos.responses.CartResponseDto;
import com.semicolon.kioskApp.exception.KioskException;
import com.semicolon.kioskApp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    public ResponseEntity<?>addItemToCart(@RequestBody CartRequestDto cartRequestDto){
        CartResponseDto cartResponseDto = null;
        try{
            cartResponseDto = cartService.addItemToCart(cartRequestDto);
        } catch (KioskException ex){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(cartResponseDto);
    }
}
