package com.syntaxer.product.persistence.service;

import com.syntaxer.product.persistence.Operations;
import com.syntaxer.product.persistence.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService extends Operations<Product> {
    Page<Product> findPaginated(Pageable pageable);

    void updateStocksById(Long id, int stocks);
}
