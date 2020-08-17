package com.airline.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.airline.dto.AerodromDto;
import com.airline.dto.DetailFlight;
import com.airline.dto.KlasaDto;
import com.airline.dto.LetDto;
import com.airline.dto.SearchFlightDto;
import com.airline.dto.SedisteDto;
import com.airline.dto.UslugaDto;
import com.airline.interfaces.service.IFlightService;
import com.airline.repository.AerodromRepository;

import AirlineJPA.Let;

@Service
@SuppressWarnings("unchecked")
class FlightServiceImpl extends BaseService implements IFlightService {
	
	@Autowired
	private AerodromRepository aerodromRepository;
	
	@Override
	public ResponseEntity<List<AerodromDto>> getAllAirports(String token) {

		if(isLogged(token)) {
			
			var list = aerodromRepository.findAll();
			
			var airports = list.stream()
					           .map(airp -> _mapperAirport.mapFromJson(airp.getJson(), AerodromDto.class))
					           .collect(Collectors.toList());
			
			
			return ok(airports);
		} else {
			
			return unauthorizedRequest();
		}
	}
	
	@Override
	public ResponseEntity<List<LetDto>> searchFlights(String token, SearchFlightDto data, Boolean isReturn) {
		if(isLogged(token)) {
			
			List<Let> lets;
			
			if(isReturn) {
				lets = letRep.getAllFlightsByCriteria(data.getIdAerodromDo(), 
						  							  data.getIdAerodromOd(),
						  							  data.getDatumPovratka());

			} else {
				
				lets = letRep.getAllFlightsByCriteria(data.getIdAerodromOd(), 
													  data.getIdAerodromDo(),
													  data.getDatumPolaska());
			}
			
			var newList = lets.stream()
						      .map(let -> _mapperLet.mapFromJson(let.getJson(), LetDto.class))
						      .distinct()
						      .collect(Collectors.toList());
			
			return ok(newList);
			
			
		} else
			return unauthorizedRequest();
	
	}
	
	@Override
	public ResponseEntity<List<DetailFlight>> searchDetails(String token, String id) {
		if(isLogged(token)) {
			var idLet = -1;
			
			try {
				idLet = Integer.parseInt(id);
			} catch(NumberFormatException e) {
				return notAcceptable();
			}
			
			var uslugas = uslugaRep.findUslugasOnLet(idLet)
								   .stream()
								   .map(usluga -> _mapperUsluga.mapFromJson(usluga.getJson(), UslugaDto.class))
								   .collect(Collectors.toList());
			
			var klasas = klasaRep.findByIdLet(idLet)
								 .stream()
								 .map(klasa -> _mapperKlasa.mapFromJson(klasa.getJson(), KlasaDto.class))
								 .collect(Collectors.toList());
			
			var sedistas = sedisteRep.findSedistaByIdLet(idLet)
									 .stream()
									 .map(sediste -> _mapperSediste.mapFromJson(sediste.getJson(), SedisteDto.class))
									 .collect(Collectors.toList());
			
			//TODO: implementirati dovlacenje aviona iz baze za izabrani let
			
			return null;
			
		} else
			return unauthorizedRequest();
	}
}
