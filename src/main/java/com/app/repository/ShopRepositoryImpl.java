package com.app.repository;

import com.app.model.Shop;
import com.app.repository.generic.AbstractGenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Optional;

public class ShopRepositoryImpl extends AbstractGenericRepository<Shop> implements ShopRepository {
    @Override
    public Optional<Shop> findByName(String name) {
        Optional<Shop> shopOp = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction tx = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();
            Query query = entityManager.createQuery("select s from Shop s where s.name = :name");
            query.setParameter("name", name);
            shopOp = Optional.of((Shop) query.getSingleResult());
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

        return shopOp;
    }

    @Override
    public Optional<Shop> findByNameAndCountry(String name, String country) {
        Optional<Shop> shopOptional = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction tx = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();
            Query query = entityManager.createQuery("select s from Shop s where s.name = :name and s.getCountry.name = :country");
            query.setParameter("name", name);
            query.setParameter("country", country);
            shopOptional = Optional.of((Shop) query.getSingleResult());
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

        return shopOptional;
    }
}
