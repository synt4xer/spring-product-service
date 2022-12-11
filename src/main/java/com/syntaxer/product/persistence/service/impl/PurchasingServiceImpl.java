package com.syntaxer.product.persistence.service.impl;

import com.syntaxer.product.persistence.model.Purchasing;
import com.syntaxer.product.persistence.repository.PurchasingRepository;
import com.syntaxer.product.persistence.service.ProductService;
import com.syntaxer.product.persistence.service.PurchasingService;
import com.syntaxer.product.persistence.service.common.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PurchasingServiceImpl extends CrudService<Purchasing> implements PurchasingService {

    private final ProductService productService;
    private final PurchasingRepository purchasingRepository;

    public PurchasingServiceImpl(ProductService productService, PurchasingRepository purchasingRepository) {
        this.productService = productService;
        this.purchasingRepository = purchasingRepository;
    }

    @Override
    public Page<Purchasing> findPaginated(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    @Override
    public Purchasing create(Purchasing entity) {
        this.productService.updateStocksById(entity.getProductId(), entity.getQuantity());
        return super.create(entity);
    }

    @Override
    protected JpaRepository<Purchasing, Long> getRepository() {
        return this.purchasingRepository;
    }
}
