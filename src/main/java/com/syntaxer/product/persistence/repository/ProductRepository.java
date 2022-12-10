package com.syntaxer.product.persistence.repository;

import com.syntaxer.product.persistence.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
