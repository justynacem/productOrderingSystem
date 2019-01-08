package com.app.repository;

import com.app.model.Producer;
import com.app.repository.generic.AbstractGenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Optional;

public class ProducerRepositoryImpl extends AbstractGenericRepository<Producer> implements ProducerRepository {
    @Override
    public Optional<Producer> findByNameCountryTrade(String name, String country, String trade) {
        Optional<Producer> producerOp = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction tx = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();
            Query query = entityManager.createQuery("select p from Producer p where p.name = :name " +
                    "and p.getCountry.name = :country and p.getTrade.name = :trade");
            query.setParameter("name", name);
            query.setParameter("country", country);
            query.setParameter("trade", trade);
            producerOp = Optional.of((Producer) query.getSingleResult());
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

        return producerOp;
    }

    @Override
    public Optional<Producer> findByName(String name) {
        Optional<Producer> producerOptional = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction tx = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();
            Query query = entityManager.createQuery("select p from Producer p where p.name = :name");
            query.setParameter("name", name);
            producerOptional = Optional.of((Producer) query.getSingleResult());
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

        return producerOptional;
    }
}
