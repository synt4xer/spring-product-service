package com.syntaxer.product.persistence.service.common;

import com.syntaxer.product.persistence.Operations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Transactional
public abstract class CrudService<T extends Serializable> implements Operations<T> {

    protected abstract JpaRepository<T, Long> getRepository();

    @Override
    @Transactional(readOnly = true)
    public T findById(long id) {
        return getRepository().findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return new ArrayList<>(getRepository().findAll());
    }

    @Override
    public Page<T> findPaginated(int page, int size) {
        return getRepository().findAll(PageRequest.of(page, size));
    }

    @Override
    public T create(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public void update(T entity) {
        getRepository().save(entity);
    }

    @Override
    public void delete(T entity) {
        getRepository().delete(entity);
    }

    @Override
    public void deleteById(long entityId) {
        getRepository().deleteById(entityId);
    }
}
