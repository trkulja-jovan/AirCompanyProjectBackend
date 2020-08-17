package com.airline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import AirlineJPA.Sediste;

@Repository
public interface SedisteRepository extends JpaRepository<Sediste, Integer>{
	
	@Query("select s from Sediste s where s.let.idLet like :idLet")
	List<Sediste> findSedistaByIdLet(@Param("idLet") Integer idLet);

}
