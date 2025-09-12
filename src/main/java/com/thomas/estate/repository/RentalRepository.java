package com.thomas.estate.repository;

import org.springframework.data.repository.CrudRepository;

import com.thomas.estate.model.Rental;

public interface RentalRepository extends CrudRepository<Rental, Integer> {

}
