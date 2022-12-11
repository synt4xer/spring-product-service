package com.syntaxer.product.persistence.service.impl;

import com.syntaxer.product.persistence.model.Product;
import com.syntaxer.product.persistence.repository.ProductRepository;
import com.syntaxer.product.persistence.service.ProductService;
import com.syntaxer.product.persistence.service.common.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl extends CrudService<Product> implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> findPaginated(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    @Override
    public void updateStocksById(Long id, int stocks) {
        Product product = getRepository().findById(id).orElse(null);

        if (product != null && product.getStocks() != 0) {
            int productStocks = product.getStocks() + stocks;
            product.setStocks(productStocks);
            this.productRepository.updateStocks(id, productStocks);
        }
    }

    @Override
    protected JpaRepository<Product, Long> getRepository() {
        return this.productRepository;
    }
}
