package com.semicolon.kioskApp.service;

import com.semicolon.kioskApp.dtos.requests.CartRequestDto;
import com.semicolon.kioskApp.dtos.requests.CartUpdateDto;
import com.semicolon.kioskApp.dtos.responses.CartResponseDto;
import com.semicolon.kioskApp.dtos.responses.QuantityOperation;
import com.semicolon.kioskApp.exception.CustomerNotFoundException;
import com.semicolon.kioskApp.exception.KioskException;
import com.semicolon.kioskApp.exception.ProductDoesNotExistException;
import com.semicolon.kioskApp.model.Cart;
import com.semicolon.kioskApp.model.Customer;
import com.semicolon.kioskApp.model.Item;
import com.semicolon.kioskApp.model.Product;
import com.semicolon.kioskApp.repository.CartRepository;
import com.semicolon.kioskApp.repository.CustomerRepository;
import com.semicolon.kioskApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public CartResponseDto addItemToCart(CartRequestDto requestDto) throws KioskException {
        Customer foundCustomer = customerRepository.findById(requestDto.getCustomerId()).
                orElseThrow(()-> new CustomerNotFoundException("Customer with ID"+requestDto.getCustomerId()+" not found!"));

        Product foundProduct = productRepository.findById(requestDto.getProductId()).
                orElseThrow(()-> new ProductDoesNotExistException("Product with ID "+requestDto.getProductId()+" not found!"));

        Item item = new Item(foundProduct, requestDto.getQuantity());

        if(!quantityIsValid(foundProduct,requestDto.getQuantity())){
            throw new KioskException("Quantity too large!");
        }
        Cart customerCart = foundCustomer.getMyCart();

        customerCart.addItem(item);
        customerCart.setTotalPrice(customerCart.getTotalPrice() +calculatePrice(item) );

        cartRepository.save(customerCart);

        return CartResponseDto.builder()
                .itemList(customerCart.getItems())
                .totalPrice(customerCart.getTotalPrice())
                .build();
    }

    private double calculatePrice(Item item) {
        return item.getProduct().getPrice() * item.getQuantityAddedToCart();
    }

    private boolean quantityIsValid(Product foundProduct, int quantity) {
        return foundProduct.getQuantity() >= quantity;
    }

    @Override
    public CartResponseDto updateCartItem(CartUpdateDto cartUpdateDto) throws KioskException {
        Customer foundCustomer = customerRepository.findById(cartUpdateDto.getCustomerId()).
                orElseThrow(()-> new CustomerNotFoundException("Customer with ID "+cartUpdateDto.getCustomerId()+" not found!"));

        Cart foundCustomerCart = foundCustomer.getMyCart();

        Item foundItem = findItemInCart(cartUpdateDto.getItemId(),foundCustomerCart);

        if(cartUpdateDto.getQuantityOp() == QuantityOperation.INCREASE){
            foundItem.setQuantityAddedToCart(foundItem.getQuantityAddedToCart() + 1);
            foundCustomerCart.setTotalPrice(foundCustomerCart.getTotalPrice() - foundItem.getProduct().getPrice());
        }
        cartRepository.save(foundCustomerCart);
        return CartResponseDto.builder()
                .itemList(foundCustomerCart.getItems())
                .totalPrice(foundCustomerCart.getTotalPrice())
                .build();
    }

    private Item findItemInCart(Long itemId, Cart foundCustomerCart) throws KioskException {
        return foundCustomerCart.getItems().stream().filter(x->x.getId().equals(itemId)).findFirst().
                orElseThrow(()-> new KioskException("Item not found!"));
    }
}
