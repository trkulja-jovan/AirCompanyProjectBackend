package com.airline.service;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;

import com.airline.dto.AerodromDto;
import com.airline.dto.FullUserDto;
import com.airline.dto.KlasaDto;
import com.airline.dto.LetDto;
import com.airline.dto.SedisteDto;
import com.airline.dto.UslugaDto;
import com.airline.mapper.IMapper;
import com.airline.repository.KlasaRepository;
import com.airline.repository.KorisnikRepository;
import com.airline.repository.LetRepository;
import com.airline.repository.LoginRepository;
import com.airline.repository.SedisteRepository;
import com.airline.repository.UslugaRepository;

import AirlineJPA.Aerodrom;
import AirlineJPA.Klasa;
import AirlineJPA.Korisnik;
import AirlineJPA.Let;
import AirlineJPA.Sediste;
import AirlineJPA.Usluga;

@SuppressWarnings("rawtypes")
public class BaseService {
	
	private static Map<String, String> loginUsers;
	
	@Autowired
	protected @Lazy KorisnikRepository userRep;
	
	@Autowired
	protected @Lazy LoginRepository loginRep;
	
	@Autowired
	protected @Lazy LetRepository letRep;
	
	@Autowired
	protected @Lazy UslugaRepository uslugaRep;
	
	@Autowired
	protected @Lazy KlasaRepository klasaRep;
	
	@Autowired
	protected @Lazy SedisteRepository sedisteRep;
	
	@Autowired
	protected @Lazy IMapper<Korisnik, FullUserDto> _mapper;
	
	@Autowired
	protected @Lazy IMapper<Aerodrom, AerodromDto> _mapperAirport;
	
	@Autowired
	protected @Lazy IMapper<Let, LetDto> _mapperLet;
	
	@Autowired
	protected @Lazy IMapper<Usluga, UslugaDto> _mapperUsluga;
	
	@Autowired
	protected @Lazy IMapper<Klasa, KlasaDto> _mapperKlasa;
	
	@Autowired
	protected @Lazy IMapper<Sediste, SedisteDto> _mapperSediste;
	
	static {
		loginUsers = new HashMap<String, String>();
	}
	
	protected Boolean addLoginUser(String username, String token) {
		
		loginUsers.put(username, token);
		return true;
	}
	
	protected static Boolean removeLoginUser(String username, String token) {
		return loginUsers.remove(username, token);
	}
	
	protected FullUserDto getUserForToken(String token) {
		
		var username = loginUsers.entrySet()
				                 .stream()
				                 .filter(x -> x.getValue().equals(token))
				                 .map(Map.Entry::getKey)
				                 .findFirst()
				                 .get();
		
		var usrFromRep = userRep.findUserByUsername(username);
		
		return _mapper.map(usrFromRep, FullUserDto.class);
		
	}
	
	protected static Boolean isLogged(String token) {
		return loginUsers.containsValue(token);
	}
	
	protected Boolean existsUserByPassword(String username, String password) {
		return loginRep.existsLogindataByUsernameAndPassword(username, password) != null;
	}
	
	protected Boolean existsUser(String username) {
		return loginRep.existsLogindataByUsername(username);
	}
	
	protected static ResponseEntity notAcceptable() {
		return new ResponseEntity<>(NOT_ACCEPTABLE);
	}
	
	protected static ResponseEntity unauthorizedRequest() {
		return new ResponseEntity<>(UNAUTHORIZED);
	}
	
	protected static ResponseEntity badRequest() {
		return new ResponseEntity<>(BAD_REQUEST);
	}
	
	protected static <T> ResponseEntity<T> ok(T body){
		return new ResponseEntity<T>(body, OK);
	}
}
