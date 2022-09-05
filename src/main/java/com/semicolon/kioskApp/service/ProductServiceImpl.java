package com.semicolon.kioskApp.service;

import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.semicolon.kioskApp.dtos.requests.ProductDto;
import com.semicolon.kioskApp.exception.KioskException;
import com.semicolon.kioskApp.exception.ProductAlreadyExistException;
import com.semicolon.kioskApp.model.Product;
import com.semicolon.kioskApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CloudService cloudService;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(ProductDto productDto) throws KioskException, IOException {
        Optional<Product> foundProduct =  productRepository.findByName(productDto.getName());
        if(foundProduct.isPresent()){
            throw new ProductAlreadyExistException("Product already exist!");

        }
        Product product = new Product();

        if(productDto.getImage() != null){
            Map<?,?> uploadResult = cloudService.upload(productDto.getImage().getBytes(),
                    ObjectUtils.asMap("public_id","inventory/" + productDto.getImage().getOriginalFilename(),
                            "overwrite", true));
            product.setImageUrl(uploadResult.get("url").toString());
        }
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setDescription(productDto.getDescription());
        return productRepository.save(product);
    }

    @Override
    public Product saveOrUpdateProduct(Product product) throws KioskException {
        if(product == null) throw  new KioskException("Product cannot be null!");
        return productRepository.save(product);
    }

    @Override
    public Product updateProductDetails(Long productId, JsonPatch patch) throws KioskException, JsonPatchException, JsonProcessingException {
        return null;
    }
}
