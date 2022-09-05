package com.semicolon.kioskApp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.semicolon.kioskApp.dtos.requests.ProductDto;
import com.semicolon.kioskApp.exception.KioskException;
import com.semicolon.kioskApp.model.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product createProduct(ProductDto productDto) throws KioskException, IOException;
    Product saveOrUpdateProduct(Product product) throws KioskException;
    Product updateProductDetails(Long productId, JsonPatch patch) throws KioskException, JsonPatchException, JsonProcessingException;
}
