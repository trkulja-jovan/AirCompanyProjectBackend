package com.airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import AirlineJPA.Aerodrom;

@Repository
public interface AerodromRepository extends JpaRepository<Aerodrom, Integer> {

}
