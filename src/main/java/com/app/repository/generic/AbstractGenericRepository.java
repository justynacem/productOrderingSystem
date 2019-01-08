package com.app.repository.generic;

import com.app.exceptions.MyException;

import javax.persistence.*;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public abstract class AbstractGenericRepository<T> implements GenericRepository<T> {

    private EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("HBN");

    private Class<T> entityClass
            = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    protected EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    ;

    @Override
    public void addOrUpdate(T t) {
        EntityManager entityManager = null;
        EntityTransaction tx = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();
            entityManager.merge(t);
            tx.commit();
        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }

            throw new MyException("REPOSITORY, ADD OR UPDATE, " + entityClass.getCanonicalName(), LocalDateTime.now());
        } finally {

            if (entityManager != null) {
                entityManager.close();
            }

        }
    }

    @Override
    public void delete(Long id) {
        EntityManager entityManager = null;
        EntityTransaction tx = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();
            T t = entityManager.find(entityClass, id);
            entityManager.remove(t);
            tx.commit();
        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }

            throw new MyException("REPOSITORY, DELETE, " + entityClass.getCanonicalName(), LocalDateTime.now());
        } finally {

            if (entityManager != null) {
                entityManager.close();
            }

        }
    }

    @Override
    public void deleteAll() {
        EntityManager entityManager = null;
        EntityTransaction tx = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();
            List<T> items = entityManager
                    .createQuery("select item from " + entityClass.getCanonicalName() + " item", entityClass)
                    .getResultList();
            for (T t : items) {
                entityManager.remove(t);
            }
            tx.commit();
        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new MyException("REPOSITORY, DELETE ALL, " + entityClass.getCanonicalName(), LocalDateTime.now());
        } finally {

            if (entityManager != null) {
                entityManager.close();
            }

        }
    }

    @Override
    public Optional<T> findById(Long id) {
        Optional<T> item = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction tx = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();
            item = Optional.of(entityManager.find(entityClass, id));
            tx.commit();
        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new MyException("REPOSITORY, FIND ONE, " + entityClass.getCanonicalName(), LocalDateTime.now());
        } finally {

            if (entityManager != null) {
                entityManager.close();
            }

        }

        return item;
    }

    @Override
    public List<T> findAll() {
        List<T> items = null;
        EntityManager entityManager = null;
        EntityTransaction tx = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();

            Query query = entityManager.createQuery("select item from " + entityClass.getCanonicalName() + " item");
            items = query.getResultList();
            tx.commit();
        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw new MyException("REPOSITORY, FIND ALL, " + entityClass.getCanonicalName(), LocalDateTime.now());
        } finally {

            if (entityManager != null) {
                entityManager.close();
            }

        }
        return items;
    }
}

