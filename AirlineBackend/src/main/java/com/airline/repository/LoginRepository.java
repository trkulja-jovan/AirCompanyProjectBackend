package com.airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import AirlineJPA.Logindata;

@Repository
public interface LoginRepository extends JpaRepository<Logindata, Integer>{
	
	Boolean existsLogindataByUsername(String username);
	
	@Query("""
			select l from Logindata l where l.username like :username and
			                                l.password like :password
			""")
	Logindata existsLogindataByUsernameAndPassword(@Param("username") String username, 
												 @Param("password") String password);
	
	@Query("""
			select l from Logindata l where l.username like :username
			""")
	Logindata getPasswordByUsername(@Param("username") String username);

}
