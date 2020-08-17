package com.airline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import AirlineJPA.Klasa;

@Repository
public interface KlasaRepository extends JpaRepository<Klasa, Integer>{
	
	@Query("select k from Klasa k where k.let.idLet like :idLet")
	List<Klasa> findByIdLet(@Param("idLet") Integer idLet);

}
