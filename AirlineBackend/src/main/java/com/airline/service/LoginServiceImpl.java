package com.airline.service;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.airline.dto.FullUserDto;
import com.airline.dto.TokenDto;
import com.airline.dto.UserDto;
import com.airline.interfaces.service.ILoginService;
import com.airline.mapper.IMapper;
import com.airline.repository.KorisnikRepository;

import AirlineJPA.Korisnik;

@Service
class LoginServiceImpl extends BaseService implements ILoginService {
	
	@Autowired
	KorisnikRepository registerR;
	
	@Autowired
	IMapper<FullUserDto, Korisnik> _mapper;
	
	@Override
	public ResponseEntity<TokenDto> tryToLogin(UserDto user) {
		
		if(super.existsUser(user.getUsername())) {
			var token = BCrypt.gensalt(31);
			
			var tokenData = new TokenDto();
			tokenData.setToken(token);
			tokenData.setUser(user);
			
			super.addLoginUser(user.getUsername(), token);
			
			return ResponseEntity.ok(tokenData);
		}
		
		return new ResponseEntity<>(BAD_REQUEST);
	}
	
	@Override
	public ResponseEntity<Boolean> registerUser(FullUserDto data) {
		
		try {
			
			var user = _mapper.map(data, Korisnik.class);
			
			registerR.save(user);
			
			return ResponseEntity.ok(true);
			
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(false);
		}
	}
	
	@Override
	public ResponseEntity<Boolean> checkValidity(String body){
		
		var p = new JSONParser(body);
		
		try {
			var username = (String) p.object().get("username");
			
			return ResponseEntity.ok(super.existsUser(username));
			
		} catch(ParseException e) {
			return ResponseEntity.ok(false);
		}
		
	}
}
