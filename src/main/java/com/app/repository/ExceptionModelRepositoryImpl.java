package com.app.repository;

import com.app.model.ExceptionModel;
import com.app.repository.generic.AbstractGenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Optional;

public class ExceptionModelRepositoryImpl extends AbstractGenericRepository<ExceptionModel> implements ExceptionModelRepository {

    @Override
    public Optional<ExceptionModel> findByName(String name) {
        Optional<ExceptionModel> exceptionModelOp = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction tx = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();
            Query query = entityManager.createQuery("select e from ExceptionModel e where e.name = :name");
            query.setParameter("name", name);
            exceptionModelOp = Optional.of((ExceptionModel) query.getSingleResult());
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

        return exceptionModelOp;
    }
}
