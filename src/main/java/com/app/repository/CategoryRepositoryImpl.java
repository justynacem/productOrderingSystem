package com.app.repository;

import com.app.model.Category;
import com.app.repository.generic.AbstractGenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Optional;

public class CategoryRepositoryImpl extends AbstractGenericRepository<Category> implements CategoryRepository {
    @Override
    public Optional<Category> findByName(String name) {
        Optional<Category> categoryOp = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction tx = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();
            Query query = entityManager.createQuery("select c from Category c where c.name = :name");
            query.setParameter("name", name);
            categoryOp = Optional.of((Category) query.getSingleResult());
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

        return categoryOp;
    }
}
