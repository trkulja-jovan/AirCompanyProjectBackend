package com.airline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airline.dto.Rezervacija;
import com.airline.dto.SearchFlightDto;
import com.airline.interfaces.service.IFlightService;

@RestController
@RequestMapping("/flights")
public class FlightController {
	
	@Autowired
	IFlightService service;
	
	@PostMapping("/getAirports")
	public ResponseEntity<?> getAirports(@RequestHeader(name="Authorization") String token){
		return service.getAllAirports(token);
	}
	
	@PostMapping("/searchFlights")
	public ResponseEntity<?> search(@RequestHeader(name="Authorization") String token,
								   	@RequestBody SearchFlightDto data){
		return service.searchFlights(token, data, false);
	}
	
	@PostMapping("/searchReturnFlights")
	public ResponseEntity<?> searchReturnFlights(@RequestHeader(name="Authorization") String token,
								   		       	 @RequestBody SearchFlightDto data){
		
		return service.searchFlights(token, data, true);
	}
	
	@PostMapping("/reserve")
	public ResponseEntity<?> reserveTicket(@RequestHeader(name="Authorization") String token,
										   @RequestBody Rezervacija rezervacija){
		return service.reserveFlight(token, rezervacija);
	}
	
	@GetMapping("/checkIn/{oznakaLeta}")
	public ResponseEntity<?> checkIn(@RequestHeader(name="Authorization") String token,
									 @PathVariable String oznakaLeta){
		return service.checkIn(token, oznakaLeta);
	}
	
	@GetMapping("/details/{idLet}")
	public ResponseEntity<?> searchDetails(@RequestHeader(name="Authorization") String token,
										   @PathVariable String idLet){
		return service.searchDetails(token, idLet);
	}
	
	@GetMapping("/history/{username}")
	public ResponseEntity<?> getHistoryDetails(@RequestHeader(name="Authorization") String token,
											   @PathVariable String username){
		return service.historyDetails(token, username);
	}

}
