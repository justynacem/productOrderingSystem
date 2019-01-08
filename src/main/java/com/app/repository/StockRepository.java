package com.app.repository;

import com.app.model.Product;
import com.app.model.Stock;
import com.app.repository.generic.GenericRepository;

import java.util.Optional;

public interface StockRepository extends GenericRepository<Stock> {
    Optional<Stock> findByProduct(Product product);

    Optional<Stock> findByProductAndShop(Long productId, Long shopId);
}
