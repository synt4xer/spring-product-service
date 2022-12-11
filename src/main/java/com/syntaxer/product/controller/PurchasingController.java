package com.syntaxer.product.controller;

import com.google.common.base.Preconditions;
import com.syntaxer.product.exception.MyResourceNotFoundException;
import com.syntaxer.product.persistence.model.Purchasing;
import com.syntaxer.product.persistence.service.PurchasingService;
import com.syntaxer.product.util.RestPreconditions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("purchasing")
public class PurchasingController {
    private final PurchasingService purchasingService;

    public PurchasingController(PurchasingService purchasingService) {
        this.purchasingService = purchasingService;
    }

    @GetMapping
    public List<Purchasing> findAll() {
        return purchasingService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Purchasing findById(@PathVariable("id") final Long id) {
        try {
            return RestPreconditions.checkFound(purchasingService.findById(id));
        } catch (MyResourceNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Purchasing Not Found", exception);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Purchasing create(@RequestBody final Purchasing resource) {
        Preconditions.checkNotNull(resource);
        return purchasingService.create(resource);
    }

//    @PutMapping(value = "/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public void update(@PathVariable("id") final Long id, @RequestBody final Purchasing resource) {
//        Preconditions.checkNotNull(id);
//        Preconditions.checkNotNull(resource);
//        RestPreconditions.checkFound(purchasingService.findById(resource.getId()));
//        purchasingService.update(resource);
//    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") final Long id) {
        purchasingService.deleteById(id);
    }
}
