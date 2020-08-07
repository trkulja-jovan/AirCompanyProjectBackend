package com.airline.service;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.airline.dto.AirportDto;
import com.airline.interfaces.service.IFlightService;
import com.airline.repository.AerodromRepository;

@Service
class FlightServiceImpl extends BaseService implements IFlightService {
	
	@Autowired
	private AerodromRepository repAirport;
	
	@Override
	public ResponseEntity<List<AirportDto>> getAllAirports(String token) {
		
		if(super.isLogged(token)) {
			
			var list = repAirport.findAll();
			
			var airports = list.stream()
					           .map(airp -> {
					        	   var aer = new AirportDto();
					        	   
					        	   aer.setIdAerodrom(airp.getIdAerodrom());
					        	   aer.setCode(airp.getCode());
					        	   aer.setGrad(airp.getGrad());
					        	   aer.setDrzava(airp.getDrzava());
					        	   
					        	   return aer;
					           })
					           .collect(Collectors.toList());
			
			
			return ResponseEntity.ok(airports);
		} else {
			
			return new ResponseEntity<>(UNAUTHORIZED);
		}
	}
	
}
