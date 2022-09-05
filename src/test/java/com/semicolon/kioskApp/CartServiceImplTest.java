package com.semicolon.kioskApp;

import com.semicolon.kioskApp.dtos.requests.CartRequestDto;
import com.semicolon.kioskApp.dtos.requests.CartUpdateDto;
import com.semicolon.kioskApp.dtos.responses.CartResponseDto;
import com.semicolon.kioskApp.dtos.responses.QuantityOperation;
import com.semicolon.kioskApp.exception.KioskException;
import com.semicolon.kioskApp.model.Cart;
import com.semicolon.kioskApp.model.Customer;
import com.semicolon.kioskApp.model.Item;
import com.semicolon.kioskApp.repository.CustomerRepository;
import com.semicolon.kioskApp.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Sql(scripts = {"/db/insert.sql"})
@Slf4j
public class CartServiceImplTest {

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerRepository customerRepository;

    CartUpdateDto cartUpdateDto;

    @BeforeEach
    void setUp() {
        cartUpdateDto =CartUpdateDto.builder()
                .itemId(102L)
                .quantityOp(QuantityOperation.INCREASE)
                .customerId(5005L).build();
    }
    @Test
    @DisplayName("Add Item Test")
    void itemCanBeAddedTest() throws KioskException{
        CartRequestDto cartRequestDto = new CartRequestDto();
        cartRequestDto.setProductId(13L);
        cartRequestDto.setCustomerId(2022L);
        cartRequestDto.setQuantity(1);

        CartResponseDto response = cartService.addItemToCart(cartRequestDto);
        assertThat(response.getTotalPrice()).isNotNull();
        assertThat(response.getItemList().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Get cart price")
    void cartPriceCanBeGottenTest() throws KioskException{
        CartRequestDto cartRequestDto = new CartRequestDto();
        cartRequestDto.setProductId(17L);
        cartRequestDto.setCustomerId(2022L);
        cartRequestDto.setQuantity(2);

        CartResponseDto response = cartService.addItemToCart(cartRequestDto);
        assertThat(response.getItemList().size()).isEqualTo(1);
        assertThat(response.getTotalPrice()).isEqualTo(3068);
    }

    @Test
    @DisplayName("Update cart item details")
    void cartItemDetailsCanBeUpdated() throws KioskException{
        Optional<Customer> customer = customerRepository.findById(4002L);
        Customer foundCustomer = customer.get();
        Cart customerCart = foundCustomer.getMyCart();
        Item item = customerCart.getItems().get(0);
        assertThat(item.getQuantityAddedToCart()).isEqualTo(2);

        CartResponseDto responseDto = cartService.updateCartItem(cartUpdateDto);
        assertThat(responseDto.getItemList().get(0).getQuantityAddedToCart()).isEqualTo(3);
    }
}
