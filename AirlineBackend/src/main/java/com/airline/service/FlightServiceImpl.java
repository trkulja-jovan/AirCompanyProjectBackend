package com.airline.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.airline.dto.AerodromDto;
import com.airline.dto.LetDto;
import com.airline.dto.SearchFlightDto;
import com.airline.interfaces.service.IFlightService;
import com.airline.repository.AerodromRepository;
import com.airline.repository.LetRepository;

import AirlineJPA.Let;

@Service
@SuppressWarnings("unchecked")
class FlightServiceImpl extends BaseService implements IFlightService {
	
	@Autowired
	private AerodromRepository aerodromRepository;
	
	@Autowired
	private LetRepository letRepository;
	
	@Override
	public ResponseEntity<List<AerodromDto>> getAllAirports(String token) {

		if(super.isLogged(token)) {
			
			var list = aerodromRepository.findAll();
			
			var airports = list.stream()
					           .map(airp -> _mapperAirport.mapFromJson(airp.getJson(), AerodromDto.class))
					           .collect(Collectors.toList());
			
			
			return ok(airports);
		} else {
			
			return super.unauthorizedRequest();
		}
	}
	
	@Override
	public ResponseEntity<List<LetDto>> searchFlights(String token, SearchFlightDto data, Boolean isReturn) {
		if(isLogged(token)) {
			
			List<Let> lets;
			
			if(isReturn) {
				lets = letRepository.getAllFlightsByCriteria(data.getIdAerodromDo(), 
						  										   data.getIdAerodromOd(),
						  										   data.getDatumPovratka());

			} else {
				
				lets = letRepository.getAllFlightsByCriteria(data.getIdAerodromOd(), 
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
	public ResponseEntity<?> searchDetails(String token, String idLet) {
		if(isLogged(token)) {
			var id = -1;
			
			try {
				id = Integer.parseInt(idLet);
			} catch(NumberFormatException e) {
				return notAcceptable();
			}
			
			
			return null;
			
		} else
			return unauthorizedRequest();
	}
	
}
