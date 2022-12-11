package com.syntaxer.product.persistence.repository;

import com.syntaxer.product.persistence.model.Purchasing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasingRepository extends JpaRepository<Purchasing, Long> {

}
