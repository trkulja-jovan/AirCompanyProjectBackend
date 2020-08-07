package com.airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import AirlineJPA.Let;

@Repository
public interface LetRepository extends JpaRepository<Let, Integer>{

}
