package com.app.repository;

import com.app.model.ExceptionModel;
import com.app.repository.generic.GenericRepository;

import java.util.Optional;

public interface ExceptionModelRepository extends GenericRepository<ExceptionModel> {
    Optional<ExceptionModel> findByName(String name);
}
