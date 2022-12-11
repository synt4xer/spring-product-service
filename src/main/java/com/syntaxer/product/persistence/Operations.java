package com.syntaxer.product.persistence;

import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

public interface Operations<T extends Serializable> {
    T findById(final long id);

    List<T> findAll();

    Page<T> findPaginated(int page, int size);

    T create(final T entity);

    void update(final T entity);

    void delete(final T entity);

    void deleteById(final long entityId);
}
