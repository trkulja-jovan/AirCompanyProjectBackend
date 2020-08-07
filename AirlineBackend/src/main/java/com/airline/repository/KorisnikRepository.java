package com.airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import AirlineJPA.Korisnik;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Integer>{
	
	@Query("select k from Korisnik k where k.logindata.username like :username")
	public Korisnik findUserByUsername(@Param("username") String username);

}
