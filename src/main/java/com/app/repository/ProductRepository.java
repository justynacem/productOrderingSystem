package com.app.repository;

import com.app.model.Product;
import com.app.repository.generic.GenericRepository;

import java.util.Optional;

public interface ProductRepository extends GenericRepository<Product> {
    Optional<Product> findByName(String name);

    Optional<Product> findByNameCategoryProducer(String name, String category, String producer);

    Optional<Product> findByNameCategory(String name, String category);

}
