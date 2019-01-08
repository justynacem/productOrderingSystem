package com.app.repository;

import com.app.model.Product;
import com.app.repository.generic.AbstractGenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Optional;

public class ProductRepositoryImpl extends AbstractGenericRepository<Product> implements ProductRepository {
    @Override
    public Optional<Product> findByName(String name) {
        Optional<Product> productOp = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction tx = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();
            Query query = entityManager.createQuery("select p from Product p where p.name = :name");
            query.setParameter("name", name);
            productOp = Optional.of((Product) query.getSingleResult());
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

        return productOp;
    }

    @Override
    public Optional<Product> findByNameCategoryProducer(String name, String category, String producer) {
        Optional<Product> productOptional = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction tx = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();
            Query query = entityManager.createQuery("select p from Product p " +
                    "where p.name = :name and p.getCategory.name = :category and p.getProducer.name = :producer");
            query.setParameter("name", name);
            query.setParameter("category", category);
            query.setParameter("producer", producer);
            productOptional = Optional.of((Product) query.getSingleResult());
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

        return productOptional;
    }

    @Override
    public Optional<Product> findByNameCategory(String name, String category) {
        Optional<Product> productOpt = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction tx = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();
            Query query = entityManager.createQuery("select p from Product p " +
                    "where p.name = :name and p.category.name = :category");
            query.setParameter("name", name);
            query.setParameter("category", category);
            productOpt = Optional.of((Product) query.getSingleResult());
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

        return productOpt;
    }
}
