package com.app.repository;

import com.app.model.Country;
import com.app.repository.generic.AbstractGenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Optional;

public class CountryRepositoryImpl extends AbstractGenericRepository<Country> implements CountryRepository {
    @Override
    public Optional<Country> findByName(String name) {
        Optional<Country> countryOp = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction tx = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();
            Query query = entityManager.createQuery("select c from Country c where c.name = :name");
            query.setParameter("name", name);
            countryOp = Optional.of((Country) query.getSingleResult());
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

        return countryOp;
    }
}
