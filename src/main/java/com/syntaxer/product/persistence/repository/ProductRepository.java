package com.syntaxer.product.persistence.repository;

import com.syntaxer.product.persistence.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Modifying
    @Query("update Product p set p.stocks = :stocks where p.id = :id")
    void updateStocks(@Param(value = "id") Long id, @Param(value = "stocks") int stocks);
}
