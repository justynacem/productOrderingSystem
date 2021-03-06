package com.app.repository;

import com.app.model.Producer;
import com.app.repository.generic.GenericRepository;

import java.util.Optional;

public interface ProducerRepository extends GenericRepository<Producer> {
    Optional<Producer> findByNameCountryTrade(String name, String country, String trade);

    Optional<Producer> findByName(String name);

}
