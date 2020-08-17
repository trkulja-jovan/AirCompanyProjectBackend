package com.airline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import AirlineJPA.Usluga;

@Repository
public interface UslugaRepository extends JpaRepository<Usluga, Integer>{
	
	@Query("select u from Usluga u inner join u.lets l where l.idLet like :idLet")
	List<Usluga> findUslugasOnLet(@Param("idLet") Integer idLet);

}
