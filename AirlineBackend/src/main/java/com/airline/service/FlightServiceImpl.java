package com.airline.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.airline.dto.AerodromDto;
import com.airline.dto.DetailFlight;
import com.airline.dto.KartaDto;
import com.airline.dto.KlasaDto;
import com.airline.dto.LetDto;
import com.airline.dto.Rezervacija;
import com.airline.dto.SearchFlightDto;
import com.airline.dto.UslugaDto;
import com.airline.interfaces.service.IFlightService;
import com.airline.repository.AerodromRepository;

import AirlineJPA.Karta;
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
	public ResponseEntity<DetailFlight> searchDetails(String token, String id) {
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
			
			var cenaUsl = uslugas.stream()
					             .collect(Collectors.averagingDouble(UslugaDto::getCena));
			
			var cena = cenaUsl + 1500;
			
			var flight = new DetailFlight();
			
			flight.setCena(cena);
			flight.setKlasa(klasas);
			flight.setUsluge(uslugas);
			flight.setIdLet(idLet);
			
			return ok(flight);
			
		} else
			return unauthorizedRequest();
	}
	
	@Override
	public ResponseEntity<Boolean> reserveFlight(String token, Rezervacija rezervacija) {
		if(isLogged(token)) {
			
			var username = getUserForToken(token);
			
			var korisnik = userRep.findUserByUsername(username);
			var let = letRep.findById(rezervacija.getIdLet()).get();
			
			var brojKarte = (Math.random() * 6) + "_" + username;
			
			var karta = new Karta();
			
			karta.setBrojKarte(brojKarte);
			karta.setKorisnik(korisnik);
			karta.setLet(let);
			karta.setCena(rezervacija.getCena());
			
			if(kartaRep.save(karta) != null)
				return ok(true);
			else
				return badRequest();
		} else
			return unauthorizedRequest();
	}
	
	@Override
	public ResponseEntity<List<KartaDto>> historyDetails(String token, String username) {
		if(isLogged(token)) {
			
			var list = kartaRep.getAllKartasByCriteria(username)
							   .stream()
							   .map(k -> _mapperKarta.mapFromJson(k.getJson(), KartaDto.class))
							   .collect(Collectors.toList());
			
			return ok(list);
		} else
			return unauthorizedRequest();
	}
	
	@Override
	public ResponseEntity<Boolean> checkIn(String token, String oznakaLeta) {
		if(isLogged(token)) {
			
			return ok(true);
		} else
			return unauthorizedRequest();
	}
}
