package com.semicolon.kioskApp.product;

import com.semicolon.kioskApp.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ProductServiceImplTest {

    @BeforeEach
    void setUp(){

    }
    @Test
    void applyPatchToProductTest(){
        Product product = new Product();
        product.setName("Umbrella");
        product.setPrice(500);
        product.setDescription("Fancy umbrella");
        product.setQuantity(6);

    }
}
