package com.semicolon.kioskApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    private Product product;

    private Integer quantityAddedToCart;

    public Item(Product product, int quantityAddedToCart){
       if(quantityAddedToCart <= product.getQuantity()){
           this.quantityAddedToCart = quantityAddedToCart;}
       else{this.quantityAddedToCart = 0;}
       this.product =  product;
       }

       public void setQuantityAddedToCart(int quantityAddedToCart){
        if(quantityAddedToCart <= product.getQuantity()){
            this.quantityAddedToCart = quantityAddedToCart;
        }
        else{this.quantityAddedToCart = 0;}
}
}
