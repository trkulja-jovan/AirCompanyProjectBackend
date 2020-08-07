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
	
	private static Map<String, String> loginUsers;
	
	@Autowired
	KorisnikRepository userR;
	
	@Autowired
	LoginRepository loginRep;
	
	@Autowired
	IMapper<Korisnik, FullUserDto> _mapper;
	
	static {
		loginUsers = new HashMap<String, String>();
	}
	
	protected Boolean addLoginUser(String username, String token) {
		
		loginUsers.put(username, token);
		return true;
	}
	
	protected Boolean removeLoginUser(String username, String token) {
		try {
			loginUsers.remove(username, token);
			return true;
		} catch(Exception e) {
			return false;
		}
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
	
	protected Boolean isLogged(String token) {
		
		return loginUsers.containsValue(token);
	
	}
	
	protected Boolean existsUserByPassword(String username, String password) {
		return loginRep.existsLogindataByUsernameAndPassword(username, password) != null;
	}
	
	protected Boolean existsUser(String username) {
		return loginRep.existsLogindataByUsername(username);
	}
	
	

}
