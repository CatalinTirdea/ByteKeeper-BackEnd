package com.bytekeeper.backend.repository;

import com.bytekeeper.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
