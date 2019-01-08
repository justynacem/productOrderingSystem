package com.app.repository;

import com.app.model.EPayment;
import com.app.model.Payment;
import com.app.repository.generic.AbstractGenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Optional;

public class PaymentRepositoryImpl extends AbstractGenericRepository<Payment> implements PaymentRepository {
    @Override
    public Optional<Payment> findByName(EPayment ePayment) {
        Optional<Payment> paymentOp = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction tx = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();
            Query query = entityManager.createQuery("select p from Payment p where p.ePayment = :ePayment");
            query.setParameter("ePayment", ePayment);
            paymentOp = Optional.of((Payment) query.getSingleResult());
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

        return paymentOp;
    }
}
