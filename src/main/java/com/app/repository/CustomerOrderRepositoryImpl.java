package com.app.repository;

import com.app.model.CustomerOrder;
import com.app.repository.generic.AbstractGenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Optional;

public class CustomerOrderRepositoryImpl extends AbstractGenericRepository<CustomerOrder> implements CustomerOrderRepository {
    @Override
    public Optional<CustomerOrder> findByCustomerId(Long id) {
        Optional<CustomerOrder> customerOrderOp = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction tx = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();
            Query query = entityManager.createQuery("select c from CustomerOrder c where c.id = :id");
            query.setParameter("id", id);
            customerOrderOp = Optional.of((CustomerOrder) query.getSingleResult());
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

        return customerOrderOp;
    }
}
