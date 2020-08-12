package com.airline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airline.dto.AirportDto;
import com.airline.dto.LetDto;
import com.airline.dto.SearchFlightDto;
import com.airline.interfaces.service.IFlightService;

@RestController
@RequestMapping("/flights")
public class FlightController {
	
	@Autowired
	IFlightService service;
	
	@PostMapping("/getAirports")
	public ResponseEntity<List<AirportDto>> getAirports(@RequestHeader(name="Authorization") String token){
		return service.getAllAirports(token);
	}
	
	@PostMapping("/searchFlights")
	public ResponseEntity<List<LetDto>> search(@RequestHeader(name="Authorization") String token,
								   		       @RequestBody SearchFlightDto data){
		return service.searchFlights(token, data);
	}

}
