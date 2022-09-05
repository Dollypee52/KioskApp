package com.semicolon.kioskApp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.semicolon.kioskApp.dtos.requests.ProductDto;
import com.semicolon.kioskApp.exception.KioskException;
import com.semicolon.kioskApp.model.Product;
import com.semicolon.kioskApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    public ResponseEntity<?> getAllProduct(){
        List<Product> allProducts = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(allProducts);

    }

    public ResponseEntity<?> createProduct(@ModelAttribute ProductDto productDto){
        try{
            Product savedProduct = productService.createProduct(productDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
        }
        catch(KioskException | IOException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    @PatchMapping(path = "/{id}",consumes = "application/json-patch+json")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody JsonPatch patch){
        try{
            Product updatedProduct = productService.updateProductDetails(id,patch);
            return ResponseEntity.ok().body(updatedProduct);
        }
        catch(KioskException | JsonProcessingException | JsonPatchException ex){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }

    }
}
