package com.app.repository;

import com.app.model.Customer;
import com.app.repository.generic.AbstractGenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Optional;

public class CustomerRepositoryImpl extends AbstractGenericRepository<Customer> implements CustomerRepository {
    @Override
    public Optional<Customer> findByNameAndSurname(String name, String surname) {
        Optional<Customer> customerOp = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction tx = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();
            Query query = entityManager.createQuery("select c from Customer c where c.name = :name and c.surname = :surname");
            query.setParameter("name", name);
            query.setParameter("surname", surname);
            customerOp = Optional.of((Customer) query.getSingleResult());
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

        return customerOp;
    }
}
