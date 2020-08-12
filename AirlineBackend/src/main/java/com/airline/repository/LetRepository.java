package com.airline.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import AirlineJPA.Let;

@Repository
public interface LetRepository extends JpaRepository<Let, Integer>{
	
	@Query(""" 
			select l from Let l inner join l.aviokompanija.avions where l.podacileta.aerodrom1.idAerodrom like :idAerodromPol and l.podacileta.aerodrom2.idAerodrom like :idAerodromDol
			
		   """)
	List<Let> getAllFlightsByCriteria(@Param("idAerodromPol") Integer idAerodrom1,
									  @Param("idAerodromDol") Integer idAerodrom2);
	
	@Query(""" 
			select l from Let l inner join l.uslugas u inner join l.sedistes inner join l.klasas where l.podacileta.aerodrom1.idAerodrom like :idAerodromDol
			                      and l.podacileta.aerodrom2.idAerodrom like :idAerodromPol
			                      and l.podacileta.datumPolaska <= :datumPolaska
		   """)
	List<Let> getAllReturnFlightsByCriteria(@Param("idAerodromPol") Integer idAerodrom1,
									        @Param("idAerodromDol") Integer idAerodrom2,
									        @Param("datumPolaska") Date datumPolaska);

}
