package com.airline.interfaces.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.airline.dto.AirportDto;

public interface IFlightService {
	
	ResponseEntity<List<AirportDto>> getAllAirports(String token);
}
