package com.app.repository.generic;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T> {
    void addOrUpdate(T t);

    void delete(Long id);

    void deleteAll();

    Optional<T> findById(Long id);

    List<T> findAll();
}
