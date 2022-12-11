package com.syntaxer.product.persistence.service;

import com.syntaxer.product.persistence.Operations;
import com.syntaxer.product.persistence.model.Purchasing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PurchasingService extends Operations<Purchasing> {
    Page<Purchasing> findPaginated(Pageable pageable);
}
