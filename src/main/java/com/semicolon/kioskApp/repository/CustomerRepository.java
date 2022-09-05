package com.semicolon.kioskApp.repository;

import com.semicolon.kioskApp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
