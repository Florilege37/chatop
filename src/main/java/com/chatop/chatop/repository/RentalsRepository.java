package com.chatop.chatop.repository;

import com.chatop.chatop.entity.RentalsDB;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RentalsRepository extends CrudRepository<RentalsDB, Long> {
    Optional<RentalsDB> findById(Long id);

}
