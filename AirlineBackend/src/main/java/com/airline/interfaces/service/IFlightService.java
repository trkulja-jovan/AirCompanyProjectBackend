package com.airline.interfaces.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.airline.dto.SearchFlightDto;

@Service
public interface IFlightService {
	
	ResponseEntity<?> getAllAirports(String token);
	ResponseEntity<?> searchFlights(String token, SearchFlightDto data, Boolean isReturn);
	ResponseEntity<?> searchDetails(String token, String idLet);
}
