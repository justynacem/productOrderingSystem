package com.app.repository;

import com.app.model.Product;
import com.app.model.Stock;
import com.app.repository.generic.AbstractGenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Optional;

public class StockRepositoryImpl extends AbstractGenericRepository<Stock> implements StockRepository {
    @Override
    public Optional<Stock> findByProduct(Product product) {
        Optional<Stock> stockOp = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction tx = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();
            Query query = entityManager.createQuery("select s from Stock s where s.product = :product");
            query.setParameter("product", product);
            stockOp = Optional.of((Stock) query.getSingleResult());
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return stockOp;
    }

    @Override
    public Optional<Stock> findByProductAndShop(Long productId, Long shopId) {
        Optional<Stock> stockOptional = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction tx = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();
            Query query = entityManager.createQuery("select s from Stock s where s.product.id = :productId and s.getShop.id = :shopId ");
            query.setParameter("productId", productId);
            query.setParameter("shopId", shopId);
            stockOptional = Optional.of((Stock) query.getSingleResult());
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return stockOptional;
    }
}
