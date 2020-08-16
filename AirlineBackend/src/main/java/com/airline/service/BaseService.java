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
import com.airline.dto.LetDto;
import com.airline.mapper.IMapper;
import com.airline.repository.KorisnikRepository;
import com.airline.repository.LoginRepository;

import AirlineJPA.Aerodrom;
import AirlineJPA.Korisnik;
import AirlineJPA.Let;

@SuppressWarnings("rawtypes")
public class BaseService {
	
	private static Map<String, String> loginUsers;
	
	@Autowired
	KorisnikRepository userR;
	
	@Autowired
	LoginRepository loginRep;
	
	@Autowired
	protected @Lazy IMapper<Korisnik, FullUserDto> _mapper;
	
//	@Autowired
//	protected @Lazy IMapper<Klasa, KlasaDto> _mapperKlasa;
	
	@Autowired
	protected @Lazy IMapper<Aerodrom, AerodromDto> _mapperAirport;
	
//	@Autowired
//	protected @Lazy IMapper<Aviokompanija, AviokompanijaDto> _mapperAviokomp;
//	
//	@Autowired
//	protected @Lazy IMapper<Podacileta, PodaciLetaDto> _mapperPodaci;
	
	@Autowired
	protected @Lazy IMapper<Let, LetDto> _mapperLet;
	
	static {
		loginUsers = new HashMap<String, String>();
	}
	
	protected Boolean addLoginUser(String username, String token) {
		
		loginUsers.put(username, token);
		return true;
	}
	
	protected Boolean removeLoginUser(String username, String token) {
		return loginUsers.remove(username, token);
	}
	
	protected FullUserDto getUserForToken(String token) {
		
		var username = loginUsers.entrySet()
				                 .stream()
				                 .filter(x -> x.getValue().equals(token))
				                 .map(Map.Entry::getKey)
				                 .findFirst()
				                 .get();
		
		var usrFromRep = userR.findUserByUsername(username);
		
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
