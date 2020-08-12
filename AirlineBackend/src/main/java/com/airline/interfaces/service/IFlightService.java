package com.airline.interfaces.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.airline.dto.AirportDto;
import com.airline.dto.LetDto;
import com.airline.dto.SearchFlightDto;

public interface IFlightService {
	
	ResponseEntity<List<AirportDto>> getAllAirports(String token);
	ResponseEntity<List<LetDto>> searchFlights(String token, SearchFlightDto data); 
}
