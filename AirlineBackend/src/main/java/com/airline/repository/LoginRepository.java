package com.airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import AirlineJPA.Logindata;

@Repository
public interface LoginRepository extends JpaRepository<Logindata, Integer>{
	
	Boolean existsLogindataByUsername(String username);

}
