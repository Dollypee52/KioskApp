package com.semicolon.kioskApp.repository;

import com.semicolon.kioskApp.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller,Long> {
}
