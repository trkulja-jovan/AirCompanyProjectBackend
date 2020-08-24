package com.airline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import AirlineJPA.Karta;

public interface KartaRepository extends JpaRepository<Karta, Integer>{
	
	@Query("select k from Karta k where k.korisnik.logindata.username like :username")
	List<Karta> getAllKartasByCriteria(@Param("username") String username);
	

}
