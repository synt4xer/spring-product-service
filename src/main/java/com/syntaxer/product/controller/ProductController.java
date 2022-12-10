package com.syntaxer.product.controller;

import com.google.common.base.Preconditions;
import com.syntaxer.product.exception.MyResourceNotFoundException;
import com.syntaxer.product.persistence.model.Product;
import com.syntaxer.product.persistence.service.ProductService;
import com.syntaxer.product.util.RestPreconditions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Product findById(@PathVariable("id") final Long id) {
        try {
            return RestPreconditions.checkFound(productService.findById(id));
        } catch (MyResourceNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found", exception);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody final Product resource) {
        Preconditions.checkNotNull(resource);
        return productService.create(resource);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") final Long id, @RequestBody final Product resource) {
        Preconditions.checkNotNull(id);
        Preconditions.checkNotNull(resource);
        RestPreconditions.checkFound(productService.findById(resource.getId()));
        productService.update(resource);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") final Long id) {
        productService.deleteById(id);
    }
}
