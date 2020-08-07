package com.airline.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.airline.dto.FullUserDto;
import com.airline.mapper.IMapper;
import com.airline.repository.KorisnikRepository;
import com.airline.repository.LoginRepository;

import AirlineJPA.Korisnik;

public class BaseService {
	
	private Map<String, String> loginUsers;
	
	@Autowired
	KorisnikRepository userR;
	
	@Autowired
	LoginRepository loginRep;
	
	@Autowired
	IMapper<Korisnik, FullUserDto> _mapper;
	
	protected BaseService() { 
		loginUsers = new HashMap<String, String>();
	}
	
	protected Boolean addLoginUser(String username, String token) {
		
		loginUsers.put(username, token);
		return true;
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
	
	protected Boolean isLogged(String username) {
		
		return loginUsers.get(username) != null;
	
	}
	
	protected Boolean existsUser(String username) {
		return loginRep.existsLogindataByUsername(username);
	}
	
	

}
